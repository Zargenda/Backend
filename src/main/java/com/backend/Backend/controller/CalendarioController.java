package com.backend.Backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import com.backend.Backend.model.SQLCalendar.*;

@RestController
public class CalendarioController {

    @GetMapping("/IniciarC")
    public int prueba( @RequestParam ("Ini1") String fecha1Ini, @RequestParam ("Fin1") String fecha1Fin,@RequestParam ("Ini2") String fecha2Ini, @RequestParam ("Fin2") String fecha2Fin) throws ParseException {
        genCalendar(fecha1Ini,fecha1Fin,fecha2Ini,fecha2Fin);
        return 200;
    }
}
