/*
 *     ___      _                          
 *    /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *  / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.algoritmos.excepciones;

/**
 *
 * @author ammgc
 */
public class ParametroIncorrectoException  extends Exception {

    public ParametroIncorrectoException() {
        super();
    }

    public ParametroIncorrectoException( String sMessage ) {
        super("Parametro Incorrecto: " + sMessage);
    }
    
}
