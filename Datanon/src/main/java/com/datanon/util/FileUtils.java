/*
 *     ___      _                          
 *    /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *  / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 *
 * FileUtils.java
 * Creation date: 06-May-2017 by ilm
 * Modification date: 06-May-2017 by ilm
 * ------------------------------------------------------------------
 *
 *
 *
 */
package com.datanon.util;

/**
 *
 * @author ilm
 */
public class FileUtils {

    public static String getFilenameExtension(String filename) {
        String extension = "";
        int i = filename.lastIndexOf('.');
        int p = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
        if (i > p) {
            extension = filename.substring(i + 1);
        }
        extension = extension.toLowerCase();
        return extension;
    }
}
