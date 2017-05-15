/*
 *      ___      _                          
 *     /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *   / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.modelos;

import java.util.List;

/**
 *
 * @author ammgc
 */
public interface Modelo {
    
    public String ejecutar (List<String[]> lista, boolean[] sensibles);
    public String getPrintingName ();
}
