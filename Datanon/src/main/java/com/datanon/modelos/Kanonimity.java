/*
 *      ___      _                          
 *     /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *   / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.modelos;

import static com.datanon.util.UtilFilas.contarDuplicados;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ammgc
 */
public class Kanonimity implements Modelo, Serializable {
 
    @Override
    public  String ejecutar (List<String[]> lista, boolean[] sensibles){
         System.out.println("Llamando ejecutar()");
        System.out.println("Nro filas :"+ lista.size());
        for(String[] s: lista){
            System.out.println(Arrays.toString(s));
        }
        
        return contarDuplicados(lista) + "- Anonimity";
    }
    
    @Override
    public String getPrintingName (){
    
        return "K - anonimity";
    }
}
