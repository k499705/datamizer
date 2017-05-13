/*
 *      ___      _                          
 *     /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *   / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 *
 * UploadServlet.java
 * ------------------------------------------------------------------
 * Servlet para subir el archivo de datos
 *
 *
 */
package com.datanon.upload;

import com.datanon.util.ReadConfig;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ilm
 */
@MultipartConfig
@WebServlet(name = "Upload", urlPatterns = {"/upload"})
public class UploadServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UploadServlet.class.getName());

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part partFile = request.getPart("file");
        if (partFile == null) {
            throw new ServletException("Error en parametro: el archivo no ha sido enviado");
        }
        String filename = partFile.getSubmittedFileName();
        if (filename == null) {
            filename = "uploadedFile";
        } else {
            filename = safeFilename(filename);
        }
        Random r = new Random();
        filename = r.nextInt(99999) + "_" + filename;
        String directorio;
        try{
        directorio= ReadConfig.readString("directorioupload");
        }catch (NamingException ex){
            directorio=ReadConfig.DEFAULT_UPLOADDIR;                  
        }
        Path path = Paths.get(directorio + filename);
        // Creamos ruta si no existe
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error creando path en disco: {0}", ex.toString());
                throw ex;
            }
        }
        // Finalmente, guardamos archivos
        try (InputStream input = partFile.getInputStream()) {
            Files.copy(input, path);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error salvando archivo en disco: {0}", ex.toString());
            throw ex;
        }
        LOG.log(Level.INFO, "Archivo subido: [{0}] en: [{1}]", new Object[]{filename, path.toString()});
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(filename);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para subir archivos";
    }

    public static String safeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9_.-]", "_");
    }

}
