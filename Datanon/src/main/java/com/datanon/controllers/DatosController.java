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

import com.datanon.algoritmos.AlgoritmoEstandar;
import com.datanon.algoritmos.excepciones.ParametroIncorrectoException;
import com.datanon.datainput.CsvReader;
import com.datanon.datainput.ExcelReader;
import com.datanon.datainput.ReaderInterface;
import com.datanon.util.FileUtils;
import com.datanon.util.Niveles.Nivel;
import com.datanon.util.ReadConfig;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.naming.NamingException;

/**
 *
 * @author ilm
 */
@Named(value = "datosController")
@ViewScoped
public class DatosController implements Serializable {

    private static final Logger LOG = Logger.getLogger(DatosController.class.getName());

    private String id;
    private String[] cabecera;
    private List<String[]> datos;
    private String[] ejemploResultado;
    private boolean[] sensible;

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

    public String preEjemploResultado(int i) {
        return ejemploResultado[i];
    }
    
    public String posEjemploResultado (int i, String tipo, String nivel) throws ParametroIncorrectoException{
    
//    System.out.println(i + " " + tipo + " " + nivel);
//    System.out.println("size:" + ejemploResultado.length);
     Nivel level;
     nivel = nivel.toUpperCase();

        switch (nivel){
            
           case "LEVE": 
               level=Nivel.LEVE;
               break;
           case "MEDIO":
               level=Nivel.MEDIO;
               break;
           case "ALTO":
           default:
               level=Nivel.ALTO;
               break;          
        }
    String resultado;
    AlgoritmoEstandar a1 = new AlgoritmoEstandar ();
        switch(tipo){
            
            case "Noaccion":
                resultado= a1.anonNoAccion(ejemploResultado[i],level,false);
                break;
            case "Caracter":
                resultado=a1.anonCaracter(ejemploResultado[i],level,false);
                break;
            case "Palabra":
                resultado=a1.anonPalabra(ejemploResultado[i],level,false);
                break;
            case "Edad":
                resultado=a1.anonEdad(ejemploResultado[i],level,false);
                break;
            case "Anontotal":
            default:
                resultado=a1.anonTotal(ejemploResultado[i],level,false);
                break;
        }
    return resultado;
     
    }

    /**
     * @return the sensible
     */
    public boolean[] getSensible() {
        return sensible;
    }

    /**
     * @param sensible the sensible to set
     */
    public void setSensible(boolean[] sensible) {
        this.sensible = sensible;
    }
    
//    public void changeSensitive(int i) {
//        System.out.println("Bef: " + Arrays.toString(sensible));
//        System.out.println("Cambiando " + i + " - " + sensible[i]);
//        sensible[i] ^= true;
//        System.out.println("Cambiado - " + sensible[i]);
//        System.out.println("Aft: " + Arrays.toString(sensible));
//    }
    
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

        ReaderInterface lector;
        if (extension.equalsIgnoreCase("csv")) {
            lector = new CsvReader();// Es CSV
        } else {
            lector = new ExcelReader();// asumimos que es Excel. Tambien se puede hacer de forma que de error si es una extension desconocida
        }
        // Llamar a lector datos = ....;
        String directorio;
        try {
            directorio = ReadConfig.readString("directorioupload");
        } catch (NamingException ex) {
            directorio = ReadConfig.DEFAULT_UPLOADDIR;
        }
        try {
            datos = lector.leer(directorio + id);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error leyendo archivo de datos {0}", ex.toString());
            //Falta enviar error al usuario
        }
        cabecera = datos.get(0);
        datos.remove(0);
        
        /*
        cabecera = new String[3];
        cabecera[0] = "id";
        cabecera[1] = "nombre";
        cabecera[2] = "edad";
        datos = new ArrayList<>();
        String[] d1 = {"123", "Juan", "22"};
        datos.add(d1);
        String[] d2 = {"456", "Maria", "45"};
        datos.add(d2);
        */
        Random r = new Random();
        int alea = r.nextInt(datos.size());
        ejemploResultado = datos.get(alea);
        sensible = new boolean[cabecera.length];
        for(int i = 0; i < sensible.length; i++) {
            sensible[i] = false;
        }
        System.out.println("Ini: " + Arrays.toString(sensible));

    }

}
