package com.backend.Backend.Utils;

import com.backend.Backend.model.Asignatura;
import com.backend.Backend.model.Aula;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;


public class ExcelReader {

    private static final String FILE_NAME = "com/backend/Backend/listado207.xlsx";
    public static void main(String[] args) {
        leerAulas();
    }

    public static void leerAsignaturas() {
        try
        {
            int[] posicionesColumnas ={3,4,6,11,17,18,21};
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
                Row row = rowIterator.next();
                Long id = (long) row.getCell(posicionesColumnas[0]).getNumericCellValue();
                String nombre = row.getCell(posicionesColumnas[1]).getStringCellValue();
                Long codArea = Long.valueOf(row.getCell(posicionesColumnas[2]).getStringCellValue());
                Long codPlan = (long) row.getCell(posicionesColumnas[3]).getNumericCellValue();
                int curso = Integer.valueOf(row.getCell(posicionesColumnas[4]).getStringCellValue());
                String semestre = row.getCell(posicionesColumnas[5]).getStringCellValue();
                Asignatura asign = new Asignatura(id,nombre,codArea,codPlan,0,curso,semestre);
                System.out.println(asign.toString());



            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void leerAsignaturas2(MultipartFile file) {
        try
        {
            int[] posicionesColumnas ={3,4,6,11,17,18,21};
            Path tempDir = Files.createTempDirectory("");
            File temFile = tempDir.resolve(file.getOriginalFilename()).toFile();
            file.transferTo(temFile);
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(temFile);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Long id = (long) row.getCell(posicionesColumnas[0]).getNumericCellValue();
                String nombre = row.getCell(posicionesColumnas[1]).getStringCellValue();
                Long codArea = Long.valueOf(row.getCell(posicionesColumnas[2]).getStringCellValue());
                Long codPlan = (long) row.getCell(posicionesColumnas[3]).getNumericCellValue();
                int curso = Integer.valueOf(row.getCell(posicionesColumnas[4]).getStringCellValue());
                String semestre = row.getCell(posicionesColumnas[5]).getStringCellValue();
                Asignatura asign = new Asignatura(id,nombre,codArea,codPlan,0,curso,semestre);
                System.out.println(asign.toString());



            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void leerAulas() {
        try
        {
            File file = new File("aulas.xlsx");
            System.out.println(file.getAbsolutePath());
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(file);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            Row row = rowIterator.next();
            while (rowIterator.hasNext() )
            {
                String acronimo= "";
                Long id = 0L;
                if( row.getCell(0).getCellType() == CellType.STRING){
                    break;
                }
                else{
                     id = (long) row.getCell(0).getNumericCellValue();
                }

                if( row.getCell(1).getCellType() == CellType.STRING){
                     acronimo = row.getCell(1).getStringCellValue();
                }
                else{
                     acronimo = Double.toString(row.getCell(1).getNumericCellValue());
                }
                String nombre = row.getCell(2).getStringCellValue();
                int capacidad = (int) row.getCell(3).getNumericCellValue();
                int edificio =  (int) row.getCell(4).getNumericCellValue();
                String observaciones = row.getCell(5).getStringCellValue();
                Aula aula = new Aula(id,acronimo,nombre,capacidad,edificio,observaciones);
                System.out.println(aula);
                row = rowIterator.next();



            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void leerAulas2(MultipartFile file){
        try
        {
            Path tempDir = Files.createTempDirectory("");
            File temFile = tempDir.resolve(file.getOriginalFilename()).toFile();
            file.transferTo(temFile);
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(temFile);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            Row row = rowIterator.next();
            while (rowIterator.hasNext() )
            {
                String acronimo = "";
                Long id = 0L;
                if( row.getCell(0).getCellType() == CellType.STRING){
                    break;
                }
                else{
                    id = (long) row.getCell(0).getNumericCellValue();
                }

                if( row.getCell(1).getCellType() == CellType.STRING){
                    acronimo = row.getCell(1).getStringCellValue();
                }
                else{
                    acronimo = Double.toString(row.getCell(1).getNumericCellValue());
                }
                String nombre = row.getCell(2).getStringCellValue();
                int capacidad = (int) row.getCell(3).getNumericCellValue();
                int edificio =  (int) row.getCell(4).getNumericCellValue();
                String observaciones = row.getCell(5).getStringCellValue();;
                Aula aula = new Aula(id,acronimo,nombre,capacidad,edificio,observaciones);
                System.out.println(aula.toString());
                row = rowIterator.next();



            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }





}
