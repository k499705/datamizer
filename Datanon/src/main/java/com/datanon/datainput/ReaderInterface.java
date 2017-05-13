/*
 *     ___      _                          
 *    /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *  / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.datainput;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author ammgc
 */
public interface ReaderInterface {
    
    public List<String[]> leer(String filepath) throws IOException;
    
}
