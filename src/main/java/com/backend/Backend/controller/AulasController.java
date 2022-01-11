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
    public List<Aula>  prueba2(@RequestParam("file") MultipartFile file){
        aulaRepository.deleteAll();
        return aulaRepository.saveAll(ExcelReader.leerAulas(file));
    }
    @GetMapping("/allAulas")
    public List<Aula> allAulas(){
        return aulaRepository.findAll();
    }

    @GetMapping("/getAulas")
    public List<String> getAulas(@RequestParam("edificio") int edificio){
        return aulaRepository.getAulasbyEdificio(edificio);
    }
}
