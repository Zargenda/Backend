package com.backend.Backend.model;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class ExcelReader {

    private static final String FILE_NAME = "com/backend/Backend/listado207.xlsx";
    public static void main(String[] args) {
        leer();
    }

    public static void leer() {
        try
        {
            int[] posicionesColumnas ={3,4,6,11,17,18};
            File file = new File("listado207.xlsx");
            System.out.println(file.getAbsolutePath());

            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(file);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            while (rowIterator.hasNext())
            {
                String roww ="";
                Row row = rowIterator.next();
                for(int i: posicionesColumnas){
                    Cell cell = row.getCell(i);
                    if(CellType.NUMERIC == cell.getCellType() ){
                        roww  = roww + (int)cell.getNumericCellValue() +  " ";

                    }
                    else {
                        roww = roww + cell.getStringCellValue() + " ";
                    }
                }
                System.out.println(roww);
                roww = "";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
