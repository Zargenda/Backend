package com.backend.Backend.controller;

import com.backend.Backend.Utils.ExcelReader;
import com.backend.Backend.model.Aula;
import com.backend.Backend.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@RestController
public class AulasController {
    @Autowired
    AulaRepository aulaRepository;
    @PostMapping("/uploadAula")
    public String  prueba2(@RequestParam("file") MultipartFile file){
        ExcelReader.leerAulas(file);
        return "EXITO";
    }

    @GetMapping("/getAulas")
    public List<Aula> getAulas(){
        return aulaRepository.findAll();
    }

}
