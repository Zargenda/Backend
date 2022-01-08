package com.backend.Backend.controller;

import com.backend.Backend.Service.HorarioService;
import com.backend.Backend.model.HorarioAsignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
public class HorarioController {
    @Autowired
    HorarioService horarioService;

    @PostMapping("/upload")
    public List<HorarioAsignatura> nuevoHorario(@RequestParam("horario") List<HorarioAsignatura> horarioAsignaturas){
       return horarioService.createHorario(horarioAsignaturas);
    }
    @GetMapping("/getHorario")
    public List<HorarioAsignatura> getHorario(@RequestParam("planId") Long planId){
        return horarioService.getHorariosPlan(planId);
    }

}
