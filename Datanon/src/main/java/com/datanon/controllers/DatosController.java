/*
 *     ___      _                          
 *    /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *  / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 *
 * DatosController.java
 * Creation date: 06-May-2017 by ilm
 * Modification date: 06-May-2017 by ilm
 * ------------------------------------------------------------------
 *
 *
 *
 */
package com.datanon.controllers;

import com.datanon.util.FileUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ilm
 */
@Named(value = "datosController")
@ViewScoped
public class DatosController implements Serializable {

    private String id;
    private String[] cabecera;
    private List<String[]> datos;
    private String[] ejemploResultado;

    /**
     * Creates a new instance of DatosController
     */
    public DatosController() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the cabecera
     */
    public String[] getCabecera() {
        return cabecera;
    }

    /**
     * @param cabecera the cabecera to set
     */
    public void setCabecera(String[] cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * @return the datos
     */
    public List<String[]> getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(List<String[]> datos) {
        this.datos = datos;
    }

    /**
     * @return the ejemploResultado
     */
    public String[] getEjemploResultado() {
        return ejemploResultado;
    }

    public String creaEjemploResultado(int i) {
        return ejemploResultado[i];
    }

    /**
     * @param ejemploResultado the ejemploResultado to set
     */
    public void setEjemploResultado(String[] ejemploResultado) {
        this.ejemploResultado = ejemploResultado;
    }

    public void loadDatos() {
        if ((id == null) || (id.isEmpty())) {
            // Lanzar excepcion
        }
        String extension = FileUtils.getFilenameExtension(id);
        if (extension.equalsIgnoreCase("csv")) {
            // Es CSV
        } else {
            // asumimos que es Excel. Tambien se puede hacer de forma que de error si es una extension desconocida

        }
        // Llamar a lector datos = ....;
        cabecera = new String[3];
        cabecera[0] = "id";
        cabecera[1] = "nombre";
        cabecera[2] = "edad";
        datos = new ArrayList<>();
        String[] d1 = {"123", "Juan", "22"};
        datos.add(d1);
        String[] d2 = {"456", "Maria", "45"};
        datos.add(d2);
        Random r = new Random();
        int alea = r.nextInt(datos.size());
        ejemploResultado = datos.get(alea);
    }

}
