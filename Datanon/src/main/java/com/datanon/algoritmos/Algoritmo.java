/*
 *      ___      _                          
 *     /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *   / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.algoritmos;

import com.datanon.algoritmos.excepciones.ParametroIncorrectoException;
import com.datanon.util.Niveles;

/**
 *
 * @author ammgc
 */
public interface Algoritmo {
    
    public String anonTotal (String valor, Niveles.Nivel nivel, boolean sensible);
    public String anonNoAccion (String valor, Niveles.Nivel nivel, boolean sensible) throws ParametroIncorrectoException;
    public String anonCaracter (String valor, Niveles.Nivel nivel, boolean sensible) throws ParametroIncorrectoException;
    public String anonPalabra (String valor, Niveles.Nivel nivel, boolean sensible) throws ParametroIncorrectoException;
    public String anonEdad (String valor, Niveles.Nivel nivel, boolean sensible) throws ParametroIncorrectoException;
    
    
    
   /*No me deja usar métodos estáticos por que no están soportados por esta versión   --> Not really! A partir de Java 8 se permiten
    */
}
