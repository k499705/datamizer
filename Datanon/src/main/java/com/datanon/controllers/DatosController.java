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

import com.datanon.algoritmos.Algoritmo;
import com.datanon.algoritmos.AlgoritmoEstandar;
import com.datanon.algoritmos.excepciones.ParametroIncorrectoException;
import com.datanon.datainput.CsvReader;
import com.datanon.datainput.ExcelReader;
import com.datanon.datainput.ReaderInterface;
import com.datanon.modelos.Kanonimity;
import com.datanon.modelos.Ldiversity;
import com.datanon.modelos.Modelo;
import com.datanon.util.FileUtils;
import com.datanon.util.Niveles.Nivel;
import com.datanon.util.ReadConfig;
import com.datanon.util.UtilFilas;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private String[] ejemploResultadoOrg;
    private String[] ejemploResultado;
    private boolean[] sensible;
    private String[] tipo;
    private String[] nivel;
    private Modelo modelo;
    private String comboModelo;
    private Algoritmo algoritmo;

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

    /**
     * @param ejemploResultado the ejemploResultado to set
     */
    public void setEjemploResultado(String[] ejemploResultado) {
        this.ejemploResultado = ejemploResultado;
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

    /**
     * @return the tipo
     */
    public String[] getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String[] tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nivel
     */
    public String[] getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String[] nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the modelo
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the comboModelo
     */
    public String getComboModelo() {
        return comboModelo;
    }

    /**
     * @param comboModelo the comboModelo to set
     */
    public void setComboModelo(String comboModelo) {
        this.comboModelo = comboModelo;

        comboModelo = comboModelo.toUpperCase();
        switch (comboModelo) {
            case "LDIVERSITY":
                modelo = new Ldiversity();
                break;
            case "KANONIMITY":
            default:
                modelo = new Kanonimity();
        }
    }

    /**
     * @return the algoritmo
     */
    public Algoritmo getAlgoritmo() {
        return algoritmo;
    }

    /**
     * @param algoritmo the algoritmo to set
     */
    public void setAlgoritmo(Algoritmo algoritmo) {
        this.algoritmo = algoritmo;
    }

//    public void changeSensitive(int i) {
//        System.out.println("Bef: " + Arrays.toString(sensible));
//        System.out.println("Cambiando " + i + " - " + sensible[i]);
//        sensible[i] ^= true;
//        System.out.println("Cambiado - " + sensible[i]);
//        System.out.println("Aft: " + Arrays.toString(sensible));
//    }
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

        String directorio;
        try {
            directorio = ReadConfig.readString("directorioupload");
        } catch (NamingException ex) {
            directorio = ReadConfig.DEFAULT_UPLOADDOWNLOADDIR;
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
        ejemploResultadoOrg = Arrays.copyOf(ejemploResultado, ejemploResultado.length);
        sensible = new boolean[cabecera.length];
        tipo = new String[cabecera.length];
        nivel = new String[cabecera.length];
        for (int i = 0; i < cabecera.length; i++) {
            sensible[i] = false;
            tipo[i] = "Noaccion";
            nivel[i] = "Leve";
        }
        modelo = new Kanonimity();
        algoritmo = new AlgoritmoEstandar();
    }

    public String preEjemploResultado(int i) {
        return ejemploResultado[i];
    }

    public String posEjemploResultado(int i) throws ParametroIncorrectoException {

//    System.out.println(i + " " + tipo + " " + nivel);
//    System.out.println("size:" + ejemploResultado.length);
        String sTipo = tipo[i].toUpperCase();
        Nivel level = UtilFilas.stringToNivel(nivel[i]);
        String resultado;
        switch (sTipo) {

            case "NOACCION":
                resultado = algoritmo.anonNoAccion(ejemploResultado[i], level, false);
                break;
            case "CARACTER":
                resultado = algoritmo.anonCaracter(ejemploResultado[i], level, false);
                break;
            case "PALABRA":
                resultado = algoritmo.anonPalabra(ejemploResultado[i], level, false);
                break;
            case "EDAD":
                resultado = algoritmo.anonEdad(ejemploResultado[i], level, false);
                break;
            case "IDENTIFICADOR":
                resultado = algoritmo.anonIdentificador(ejemploResultadoOrg[i], ejemploResultadoOrg, level, false);
                break;
            case "ANONTOTAL":
            default:
                resultado = algoritmo.anonTotal(ejemploResultado[i], level, false);
                break;
        }
        return resultado;

    }

    public String resultadoModelo() throws ParametroIncorrectoException {

        System.out.println("Entrando en resultadomodelo");
        List<String[]> datosProcesados = anonimizarDatos();

//                System.out.println("Nro filas :"+ datosProcesados.size());
//        for(String[] s: datosProcesados){
//            System.out.println(Arrays.toString(s));
//        }
        return modelo.ejecutar(datosProcesados, sensible);
    }

    private List<String[]> anonimizarDatos() throws ParametroIncorrectoException {

        List<String[]> datosProcesados = new ArrayList<>();

        for (String[] fila : datos) {
            String[] filanueva = new String[fila.length];
            for (int i = 0; i < fila.length; i++) {
                String sTipo = tipo[i].toUpperCase();
                switch (sTipo) {

                    case "NOACCION":
                        filanueva[i] = algoritmo.anonNoAccion(fila[i], UtilFilas.stringToNivel(nivel[i]), false);
                        break;
                    case "CARACTER":
                        filanueva[i] = algoritmo.anonCaracter(fila[i], UtilFilas.stringToNivel(nivel[i]), false);
                        break;
                    case "PALABRA":
                        filanueva[i] = algoritmo.anonPalabra(fila[i], UtilFilas.stringToNivel(nivel[i]), false);
                        break;
                    case "EDAD":
                        filanueva[i] = algoritmo.anonEdad(fila[i], UtilFilas.stringToNivel(nivel[i]), false);
                        break;
                    case "IDENTIFICADOR":
                        filanueva[i] = algoritmo.anonIdentificador(fila[i], fila, UtilFilas.stringToNivel(nivel[i]), false);
                        break;
                    case "ANONTOTAL":
                    default:
                        filanueva[i] = algoritmo.anonTotal(fila[i], UtilFilas.stringToNivel(nivel[i]), false);
                        break;
                }
            }
            datosProcesados.add(filanueva);

        }
        return datosProcesados;
    }

    private void obtenerFila(int i, String separador) {

    }

    public void download() throws IOException, ParametroIncorrectoException {

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Random r = new Random();
        int ale = r.nextInt(99999);
        String filename = "datanon_" + ale + "_" + id + ".csv";
        List<String[]> datosprocesados = anonimizarDatos();

        String directorio;
        try {
            directorio = ReadConfig.readString("directoriodownload");
        } catch (NamingException ex) {
            directorio = ReadConfig.DEFAULT_UPLOADDOWNLOADDIR;
        }
        Path path = Paths.get(directorio + filename);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error creando path en disco: {0}", ex.toString());
                throw ex;
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(directorio + filename), ',')) {
            writer.writeAll(datosprocesados);
        }

        try (InputStream input = Files.newInputStream(path)) {
            ec.responseReset();
            ec.setResponseContentType("application/octet-stream");
            ec.setResponseHeader("Content-Disposition", String.format("attachment; filename=%s", filename));

            try (OutputStream output = ec.getResponseOutputStream()) {
                byte[] outputByte = new byte[4096];
                int iNReadBytes;
                int fileSize = 0;
                while ((iNReadBytes = input.read(outputByte, 0, 4096)) != -1) {
                    output.write(outputByte, 0, iNReadBytes);
                    fileSize = fileSize + iNReadBytes;
                }
                ec.setResponseContentLength(fileSize);
            }
        }

        fc.responseComplete();
    }

}
