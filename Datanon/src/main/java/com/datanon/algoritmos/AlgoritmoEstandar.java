/*
 *      ___      _                          
 *     /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *   / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.algoritmos;

import com.datanon.clasesestandar.Niveles;
import java.util.logging.Logger;

/**
 *
 * @author ammgc
 */
public class AlgoritmoEstandar implements Niveles,Algoritmo{

    private static final Logger LOG = Logger.getLogger(AlgoritmoEstandar.class.getName());
     

    @Override
    public  String anonTotal (String valor, Nivel nivel, boolean sensible){
 
        return "***";
        }
        
    @Override
    public  String anonNoAccion (String valor, Nivel nivel, boolean sensible){
        
        return valor;
    }

    @Override
    public  String anonCaracter (String valor, Nivel nivel, boolean sensible){
         
        String anonimizado=valor;
        switch (nivel){
            
            case LEVE:    anonimizado=valor.substring(0,valor.length()-1)+"*";                          
                            break;
                
            case MEDIO:   anonimizado=valor.substring(0,valor.length()/2);
                          for (int i=0; i<=(valor.length()/2); i++){
                              anonimizado=anonimizado+"*";
                          }
                            break;
                
            case ALTO:    anonimizado="***";
                            break;
            default:    
        }
        
        return anonimizado;
    }
    @Override
    public  String anonPalabra (String valor, Nivel nivel, boolean sensible){
        
        String[] anonimizado = new String[5];
        switch (nivel){
            
            case LEVE:   anonimizado=valor.split(" ");
                         for(int i=(anonimizado.length/2); i<=anonimizado.length;i++){
                             anonimizado[i]="*";
                         }
                            break;
                
            case MEDIO:   anonimizado=valor.split(" ");
                         for(int i=0; i<=anonimizado.length;i++){
                             anonimizado[i]="*";
                         }
                            break;
                
            case ALTO:    anonimizado=valor.split(" ");
                        for(int i=0; i<=anonimizado.length;i++){
                             anonimizado[i]="*";
                         }
                            break;
            default:    
        }
        
        return anonimizado[5];
    }
    
    @Override
    public  String anonNumeros (String valor, Nivel nivel, boolean sensible){
        
        double anonimizadodouble;
        String anonimizado="";
        
        switch (nivel){
            
            case LEVE:    anonimizadodouble= Double.parseDouble(valor);
                          if (anonimizadodouble>=1 && anonimizadodouble<=120){ //Es una edad
                              if (anonimizadodouble<100){                      //Si la edad es menor que 100 anonimizo el segundo digito
                              anonimizado=String.valueOf(anonimizadodouble);                             
                              anonimizado=anonimizado.substring(0,anonimizado.length()-1)+"*";
                              }
                              if (anonimizadodouble>100){                      //Si la edad es mayor que 100 anonimizo los 2 últimos dígitos 
                              anonimizado=String.valueOf(anonimizadodouble);
                              anonimizado=anonimizado.substring(0,anonimizado.length()-1)+"**";
                              }
                          }
                          else{                           
                              anonimizado= String.valueOf(anonimizadodouble);
                              anonimizado=anonimizado.substring(0,anonimizado.length()-1)+"*";
                          }
                            break;
                
            case MEDIO:   anonimizadodouble= Double.parseDouble(valor);
                            if (anonimizadodouble>=1 && anonimizadodouble<=120){
                                anonimizado=String.valueOf(anonimizadodouble-10)+"-"+String.valueOf(anonimizadodouble+10);
                            }
                            else{
                                anonimizado=String.valueOf(anonimizadodouble);  //Sustituyo por asteriscos la mitad del dato en String ej:28030-->28***
                                anonimizado=anonimizado.substring(0,anonimizado.length()/2);
                                for (int i=0; i<=(anonimizado.length()/2); i++){
                                    anonimizado=anonimizado+"*";
                                }
                            }
                            break;
                
            case ALTO:    anonimizadodouble= Double.parseDouble(valor);
                            if (anonimizadodouble>=1 && anonimizadodouble<=120){
                            anonimizado=String.valueOf(anonimizadodouble-20)+"-"+String.valueOf(anonimizadodouble+20);
                            }
                            else{
                                anonimizado="***";
                            }
                            break;
                            
                            
            default:    
        }
        
        return anonimizado;
        
    }
}
