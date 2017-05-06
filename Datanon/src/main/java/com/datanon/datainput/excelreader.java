/*
 *     ___      _                          
 *    /   \__ _| |_ __ _ _ __   ___  _ __  
 *    / /\ / _` | __/ _` | '_ \ / _ \| '_ \ 
 *  / /_// (_| | || (_| | | | | (_) | | | |
 *  /___,' \__,_|\__\__,_|_| |_|\___/|_| |_|
 *                                          
 */
package com.datanon.datainput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ammgc
 */
public class excelreader {

    public static List<String[]> excelToSignalDataList(String filepath) throws IOException {

        try(FileInputStream inputStream = new FileInputStream(new File(filepath))){
        List<String[]> rows = new ArrayList<>();
            try (Workbook workbook = new XSSFWorkbook(inputStream)) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    //String[] fila = new String[row.getLastCellNum()];
                    List<String> fila = new ArrayList<>();
                    for (Cell cell : row) {
                        fila.add(cell.getStringCellValue());
                    }
                    String[] filaarray = fila.toArray(new String[fila.size()]);
                    rows.add(filaarray);
                    
                }
            }

        return rows;
        }
    }
}
