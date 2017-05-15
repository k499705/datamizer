/*
 *      ___      _                          
 *     /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *   / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.modelos;

import static com.datanon.util.UtilFilas.contarGruposFilas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ammgc
 */
public class Ldiversity implements Modelo, Serializable {
        
    public  List<String[]> borrarBools (List<String[]> lista, boolean[] sensibles){
        
        int tamanyo = sensibles.length;        
        List<String[]> listanueva = new ArrayList<>();
        for (String[] fila : lista){
          String [] filanueva = new String[tamanyo];
          int contador = 0;
          for (int i=0; i< tamanyo; i++){
              if (!sensibles[i]){
                  filanueva[contador] = fila[i];
                  contador++;
              }
          }
          listanueva.add(filanueva);
        }
      return listanueva;  
    }
    
    
    
    @Override
    public  String ejecutar (List<String[]> lista, boolean[] sensibles){
        
        List<String[]> listanueva = borrarBools(lista,sensibles);
        return contarGruposFilas(listanueva) + "- Diversity";
    }
    
    @Override
    public String getPrintingName (){
        return "L - Diversity";
    }
}
