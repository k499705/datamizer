/*
 *      ___      _                          
 *     /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *   / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 *  DATANON -  AMM 2017
 *  upload.js
 *  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 *  Contiene funcionalidad para el drag & drop
 */

// Registering functions for the D&D events
$(document).ready(function () {
    console.log("Registrando eventos...");
    var dropbox = $('#uploadbox')[0];
    dropbox.addEventListener('dragenter', noopHandler, false);
    dropbox.addEventListener('dragexit', noopHandler, false);
    dropbox.addEventListener('dragover', noopHandler, false);
    dropbox.addEventListener('drop', dropDataFileUpload, false);
});

// Function to handle the drop event
function dropDataFileUpload(event) {
    noopHandler(event);
    var files = event.dataTransfer.files;
    $('#uploadstatus').show(300);
    upload(files[0], 'upload', 'uploadForm:subidaCompleta');
}

// A function to stop propagation of the event
function noopHandler(event) {
    event.stopPropagation();
    event.preventDefault();
}

// Function to manage the upload
function upload(file, postprocessingServlet, refreshButton) {
    console.log("Llamando upload()...");
    var fileSize = getHumanFriendlyFileSize(file);
    var mimetype = getMimeType(file);

    // Progress Bar
    $('#nombre').append(file.name);
    $('#tipo').append(mimetype);
    $('#tam').append(fileSize);
    var formData = new FormData();
    formData.append('file', file);

    var xhr = new XMLHttpRequest();
    // Handler for progress events
    xhr.upload.addEventListener('progress', function (e) {
        uploadProgress(e);
    }, false);
    // Handler for upload complete event
    xhr.addEventListener('load', function (e) {
        uploadComplete(e, refreshButton);
    }, false);
    xhr.addEventListener('error', function (e) {
        uploadError(e);
    }, false);
    xhr.open('POST', postprocessingServlet, true); // If async=false -> no progress bar support.
    xhr.send(formData);
}

// Function to convert size in bytes to a human friendly size to be displayed (e.g. "4.5 MB")
function getHumanFriendlyFileSize(file) {
    var fileSize = 0;
    if (file.size > 1073741824) {
        fileSize = (Math.round(file.size * 100 / (1073741824)) / 100).toString() + 'GB';
    } else if (file.size > 1048576) {
        fileSize = (Math.round(file.size * 100 / (1048576)) / 100).toString() + 'MB';
    } else {
        fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
    }
    return fileSize;
}

// Function to get a file MIME type
function getMimeType(file) {
    var mimetype = file.type;
    if (mimetype === '') {
        mimetype = 'Desconocido';
    }
    return mimetype;
}

// Function to update the progress bar
function uploadProgress(event) {
    // Note: doesn't work with async=false.
    var progress = Math.round(event.loaded / event.total * 100);
    $('#uploadstatus progress').val(progress);
}

// Function to execute when the upload is complete. It removes the progress bar
// and simulates a click to refresh the list of files in the JSF page
function uploadComplete(event, refreshButton) {
    console.log("Subida completada!");
    document.getElementById(refreshButton).click();
}

// Function to execute when there has been an error in the upload.
function uploadError(event) {
    console.log("Ha habido un error! ");
    console.log(event);
}
