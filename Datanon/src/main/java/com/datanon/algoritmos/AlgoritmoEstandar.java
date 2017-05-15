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
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ammgc
 */
public class AlgoritmoEstandar implements Niveles, Algoritmo, Serializable{

    private static final Logger LOG = Logger.getLogger(AlgoritmoEstandar.class.getName());

    @Override
    public String anonTotal(String valor, Nivel nivel, boolean sensible) {

        return "***";
    }

    @Override
    public String anonNoAccion(String valor, Nivel nivel, boolean sensible) throws ParametroIncorrectoException {
        if (valor == null || valor.isEmpty()) {
            throw new ParametroIncorrectoException("Valor es nulo o vacío");
        }
        return valor;
    }

    @Override
    public String anonCaracter(String valor, Nivel nivel, boolean sensible) throws ParametroIncorrectoException {
        if (valor == null || valor.isEmpty()) {
            throw new ParametroIncorrectoException("Valor es nulo o vacío");
        }
        String anonimizado;
        switch (nivel) {

            case LEVE:
                anonimizado = valor.substring(0, valor.length() - 1) + "*";
                break;

            case MEDIO:
                anonimizado = valor.substring(0, valor.length() / 2);
                for (int i = 0; i <= (valor.length() / 2); i++) {
                    anonimizado = anonimizado + "*";
                }
                break;

            case ALTO:
            default:
                anonimizado = valor.substring(0, 1);
                for (int i = 1; i <= (valor.length()); i++) {
                    anonimizado = anonimizado + "*";
                }
                break;
        }

        return anonimizado;
    }

    @Override
    public String anonPalabra(String valor, Nivel nivel, boolean sensible) throws ParametroIncorrectoException {

        if (valor == null || valor.isEmpty()) {
            throw new ParametroIncorrectoException("Valor es nulo o vacío");
        }
        /* Cambiar en funcion del numero de palabras
        LEVE: alejandro martinez morillo -> Alejandro Martinez *
        MEDIO: coja mitad de las palabras (Si longitud es uno) -> * 
        ALTO: Alejandro * 
         */
        String[] palabras;
        String anonimizado;
        palabras = valor.split(" ");
        switch (nivel) {

            case LEVE:
                palabras[palabras.length-1]="*";               
                break;

            case MEDIO:              
                for (int i = (palabras.length / 2); i < palabras.length; i++) {
                    palabras[i] = "*";
                }
                break;

            case ALTO:
            default:
                for (int i = 1; i < palabras.length; i++) {
                    palabras[i] = "*";
                }
                break;

        }
        anonimizado = String.join(" ", palabras);
        return anonimizado;
    }

    @Override
    public String anonEdad(String valor, Nivel nivel, boolean sensible) throws ParametroIncorrectoException {

        // Modificar, edad, leve --> sin numeros negativos
        // medio --> que no aparezca el 0s
        // alto --> crear más rangos
        
        if (valor == null || valor.isEmpty()) {
            throw new ParametroIncorrectoException("Valor es nulo o vacío");
        }
        int anonimizadoint;
        String anonimizado="-";
        try {
            anonimizadoint = Integer.parseInt(valor);
        } catch (NumberFormatException ex) {
            LOG.log(Level.SEVERE, "Valor pasado [{0}] no es un Integer: {1}", new Object[]{valor, ex.toString()});
            throw new ParametroIncorrectoException("Valor no es una Edad");
        }

        if (anonimizadoint >= 1 && anonimizadoint <= 120){
            
            switch (nivel) {
            
             case LEVE: 
                anonimizado = String.valueOf(anonimizadoint-3) + "-" + String.valueOf(anonimizadoint+3);
                break;
                
             case MEDIO: 
                 int result;
                 
                 result = anonimizadoint/10;
                 result *= 10;
                 anonimizado = String.valueOf(result)+"s";
                 break;
                 
             case ALTO:
                 
                 if (anonimizadoint < 20) {
                     anonimizado = "< 20";
                 }
                 if (anonimizadoint < 50) {
                     anonimizado = "< 50";
                 }
                 if (anonimizadoint < 70) {
                     anonimizado = "< 70";
                 }
                 else{
                 anonimizado = "> 70";
                 }
                 break;
            }
        }
/*
            case LEVE:
                if (anonimizadoint >= 1 && anonimizadoint <= 120) { //Es una edad
                    if (anonimizadoint < 100) {                      //Si la edad es menor que 100 anonimizo el segundo digito
                        //anonimizado=String.valueOf(anonimizadoint);                             
                        anonimizado = valor.substring(0, valor.length() - 1) + "*";
                    }
                    if (anonimizadoint > 100) {                      //Si la edad es mayor que 100 anonimizo los 2 últimos dígitos 
                        anonimizado = String.valueOf(anonimizadoint);
                        anonimizado = valor.substring(0, valor.length() - 1) + "**";
                    }
                } else {
                    //anonimizado= String.valueOf(anonimizadoint);
                    anonimizado = valor.substring(0, valor.length() - 1) + "*";
                }
                break;

            case MEDIO:
                if (anonimizadoint >= 1 && anonimizadoint <= 120) {
                    anonimizado = String.valueOf(anonimizadoint - 10) + "-" + String.valueOf(anonimizadoint + 10);
                } else {
                    anonimizado = String.valueOf(anonimizadoint);  //Sustituyo por asteriscos la mitad del dato en String ej:28030-->28***
                    anonimizado = anonimizado.substring(0, anonimizado.length() / 2);
                    for (int i = 0; i <= (anonimizado.length() / 2); i++) {
                        anonimizado = anonimizado + "*";
                    }
                }
                break;

            case ALTO:
            default:
                if (anonimizadoint >= 1 && anonimizadoint <= 120) {
                    anonimizado = String.valueOf(anonimizadoint - 20) + "-" + String.valueOf(anonimizadoint + 20);
                } else {
                    anonimizado = "***";
                }
                break;

        }
*/
        return anonimizado;

    }

    // public  String anonNumeros (String valor, Nivel nivel, boolean sensible) throws NumberFormatException{
}
