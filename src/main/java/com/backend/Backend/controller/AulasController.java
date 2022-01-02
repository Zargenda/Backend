package com.backend.Backend.controller;

import com.backend.Backend.Utils.ExcelReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@RestController
public class AulasController {
    @PostMapping("/upload")
    public String  prueba2(@RequestParam("file") MultipartFile file){
        ExcelReader.leerAulas2(file);
        return "EXITO";
    }
    @PostMapping("/reservar")
    public String reservar(@RequestParam("aulaId") Long id, @RequestParam("fecha") Date fechaInicio
            ,@RequestParam("hora") int horas ){
        return "EXITO";
    }

}
