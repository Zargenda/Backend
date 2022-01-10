package com.backend.Backend.controller;

import com.backend.Backend.Utils.ExcelReader;
import com.backend.Backend.model.Aula;
import com.backend.Backend.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/aulas")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class AulasController {
    @Autowired
    AulaRepository aulaRepository;
    @PostMapping("/uploadAula")
    public String  prueba2(@RequestParam("file") MultipartFile file){
        ExcelReader.leerAulas(file);
        return "EXITO";
    }
    @GetMapping("/allAulas")
    public List<Aula> allAulas(){
        return aulaRepository.findAll();
    }

    @GetMapping("/getAulas")
    public List<String> getAulas(@RequestParam("edificio") String edificio){
        return aulaRepository.getAulasbyEdificio(edificio);
    }
}
