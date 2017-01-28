/*
 *     ___      _                          
 *    /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *  / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.algoritmos;

import com.datanon.clasesestandar.Niveles;

/**
 *
 * @author ammgc
 */
public interface Algoritmo {
    
    public  String anonTotal (String valor, Niveles.Nivel nivel, boolean sensible);
    public  String anonNoAccion (String valor, Niveles.Nivel nivel, boolean sensible);
    public  String anonCaracter (String valor, Niveles.Nivel nivel, boolean sensible);
    public  String anonPalabra (String valor, Niveles.Nivel nivel, boolean sensible);
    public  String anonNumeros (String valor, Niveles.Nivel nivel, boolean sensible);
    
    
    
   /*No me deja usar métodos estáticos por que no están soportados por esta versión   
    */
}
