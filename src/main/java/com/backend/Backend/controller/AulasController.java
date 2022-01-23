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
    //Endpoint del API rest utilizado para subir el fichero excel correspondiente a las aulas
    @PostMapping("/uploadAula")
    public List<Aula>  prueba2(@RequestParam("file") MultipartFile file){
        aulaRepository.deleteAll();
        return aulaRepository.saveAll(ExcelReader.leerAulas(file));
    }
    //Endpoint del API rest que devuelve todas las aulas guardadas en BD
    @GetMapping("/allAulas")
    public List<Aula> allAulas(){
        return aulaRepository.findAll();
    }

    //Endpoint del API rest que devuelve las aulas de un edificio
    @GetMapping("/getAulas")
    public List<String> getAulas(@RequestParam("edificio") int edificio){
        return aulaRepository.getAulasbyEdificio(edificio);
    }
}
