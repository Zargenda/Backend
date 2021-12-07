package com.backend.Backend.controller;

import com.backend.Backend.model.Calendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static com.backend.Backend.model.SQLCalendar.*;

@RestController
public class CalendarioController {

    @GetMapping("/pruebaC")
    public int prueba( @RequestParam ("Ini1") String fecha1Ini, @RequestParam ("Fin1") String fecha1Fin,@RequestParam ("Ini2") String fecha2Ini, @RequestParam ("Fin2") String fecha2Fin) throws ParseException {
        GenCalendar(fecha1Ini,fecha1Fin,fecha2Ini,fecha2Fin);
        return 200;
    }
}
