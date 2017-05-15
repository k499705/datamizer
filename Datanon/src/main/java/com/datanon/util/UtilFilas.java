/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datanon.util;

import com.datanon.util.Niveles.Nivel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ammgc
 */
public class UtilFilas {

    public static int contarDuplicados(List<String[]> lista) {

        Map<String, Integer> mapa = crearMapaContador(lista);
        int contador = 0;
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {

            if (entry.getValue() > contador) {
                contador = entry.getValue();
            }
        }

        return contador;
    }

    public static int contarGruposFilas(List<String[]> lista) {

        Map<String, Integer> mapa = crearMapaContador(lista);
        int contador = 0;
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {

            if (entry.getValue() > contador) {
                contador = entry.getValue();
            }
        }
        int contadorrepe = 0;
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {

            if (entry.getValue() == contador) {
                contadorrepe++;
            }
        }

        return contadorrepe;
    }

    public static Map<String, Integer> crearMapaContador(List<String[]> lista) {

        Map<String, Integer> mapa = new HashMap<>();

        for (String[] row : lista) {

            String clave = String.join("##", row);
            if (!mapa.containsKey(clave)) {
                mapa.put(clave, 1);
            } else {
                int valor = mapa.get(clave);
                valor = valor + 1;
                mapa.put(clave, valor);
            }
        }
        return mapa;
    }

    public static Nivel stringToNivel(String nivel) {
        nivel = nivel.toUpperCase();
        switch (nivel) {

            case "LEVE":
                return Nivel.LEVE;
            case "MEDIO":
                return Nivel.MEDIO;
            case "ALTO":
            default:
                return Nivel.ALTO;
        }

    }
}
