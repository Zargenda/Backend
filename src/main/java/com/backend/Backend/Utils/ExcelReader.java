package com.backend.Backend.Utils;

import com.backend.Backend.model.Asignatura;
import com.backend.Backend.model.Aula;
import com.backend.Backend.repository.AsignaturaRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


public class ExcelReader {


    private static final String FILE_NAME = "com/backend/Backend/listado207.xlsx";
    public static  void main(String[] args) {

        leerAsignaturas();

    }
    private static final int[] POSICIONES_COLUMNAS = {3,4,6,11,17,18,23,5,30,12} ;
    public static List<Asignatura>  leerAsignaturas() {
        try
        {


            File file = new File("listado207.xlsx");
            System.out.println(file.getAbsolutePath());
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(file);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            return leerSheetAsignaturas(sheet);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static  void leerAsignaturas(MultipartFile file) {
        try
        {

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

    private static List<Asignatura> leerSheetAsignaturas(XSSFSheet sheet){
        //Create Workbook instance holding reference to .xlsx file
        List<Asignatura> result = new ArrayList<>();
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
                //horasPorSemana = 1;
            }
            Asignatura asign = new Asignatura(id,nombre,codArea,codPlan,grupos,curso,semestre.toUpperCase(Locale.ROOT),horasPorSemana,nombreArea);
            result.add(asign);
            //int pageCount = (  (horas* numAreas) - 1) / 12 + 1; Redondeo a la alta

            System.out.println(asign);
            //System.out.println(horas);


        }
        return result;
    }




    public static List<Aula> leerAulas() {
        try
        {
            File file = new File("aulas.xlsx");
            System.out.println(file.getAbsolutePath());
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(file);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
             return leerSheetAulas(sheet);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Aula> leerAulas(MultipartFile file){
        try
        {
            Path tempDir = Files.createTempDirectory("");
            File temFile = tempDir.resolve(file.getOriginalFilename()).toFile();
            file.transferTo(temFile);
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook =WorkbookFactory.create(temFile);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            return leerSheetAulas(sheet);
            //Iterate through each rows one by on



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Aula> leerSheetAulas(XSSFSheet sheet){
        List<Aula> result = new ArrayList<>();
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
            result.add(aula);
            row = rowIterator.next();
        }
        return result;
    }

    }







