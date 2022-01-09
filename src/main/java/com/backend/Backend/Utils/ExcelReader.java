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
        leerAsignaturas();
    }
    private static final int[] POSICIONES_COLUMNAS = {3,4,6,11,17,18,23,5,30,7} ;
    public static void leerAsignaturas() {
        try
        {
            int[] posicionesColumnas ={3,4,6,11,17,18,23,5,30,7};
            File file = new File("listado207.xlsx");
            System.out.println(file.getAbsolutePath());
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(file);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            leerSheetAsignaturas(sheet);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void leerAsignaturas(MultipartFile file) {
        try
        {
            int[] posicionesColumnas ={3,4,6,11,17,18,24,5,30};
            Path tempDir = Files.createTempDirectory("");
            File temFile = tempDir.resolve(file.getOriginalFilename()).toFile();
            file.transferTo(temFile);
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(temFile);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            leerSheetAsignaturas(sheet);
            //Iterate through each rows one by one
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void leerSheetAsignaturas(XSSFSheet sheet){
        //Create Workbook instance holding reference to .xlsx file


        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        rowIterator.next();
        rowIterator.next();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Long id = (long) row.getCell(POSICIONES_COLUMNAS[0]).getNumericCellValue();
            String nombre = row.getCell(POSICIONES_COLUMNAS[1]).getStringCellValue();
            Long codArea = Long.valueOf(row.getCell(POSICIONES_COLUMNAS[2]).getStringCellValue());
            Long codPlan = (long) row.getCell(POSICIONES_COLUMNAS[3]).getNumericCellValue();
            int curso = Integer.valueOf(row.getCell(POSICIONES_COLUMNAS[4]).getStringCellValue());
            String semestre = row.getCell(POSICIONES_COLUMNAS[5]).getStringCellValue();
            int grupos =  (int) row.getCell(POSICIONES_COLUMNAS[6]).getNumericCellValue();
            int numAreas =  (int) row.getCell(POSICIONES_COLUMNAS[7]).getNumericCellValue();
            int  horas =(int) row.getCell(POSICIONES_COLUMNAS[8]).getNumericCellValue();
            String nombreArea = row.getCell(POSICIONES_COLUMNAS[9]).getStringCellValue();
            int horasPorSemana = (numAreas * horas) / 12;
            if(horasPorSemana == 0){
                horasPorSemana = 1;
            }
            Asignatura asign = new Asignatura(id,nombre,codArea,codPlan,grupos,curso,semestre,horasPorSemana,nombreArea);

            //int pageCount = (  (horas* numAreas) - 1) / 12 + 1; Redondeo a la alta

            System.out.println(asign);
            //System.out.println(horas);


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
            leerSheetAulas(sheet);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void leerAulas(MultipartFile file){
        try
        {
            Path tempDir = Files.createTempDirectory("");
            File temFile = tempDir.resolve(file.getOriginalFilename()).toFile();
            file.transferTo(temFile);
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(temFile);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            leerSheetAulas(sheet);
            //Iterate through each rows one by on



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void leerSheetAulas(XSSFSheet sheet){
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

    }







