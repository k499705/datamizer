/*
 *     ___      _                          
 *    /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *  / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.datainput;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author ammgc
 */
public class CsvReader implements ReaderInterface {

    private static final char SEPARATOR = ',';
    
    @Override
    public List<String[]> leer(String filepath) throws IOException {
               
            CSVReader reader = new CSVReader(new FileReader(filepath), SEPARATOR);
            List<String[]> rows = reader.readAll(); 
            return rows;
        
    }
    
}