package com.backend.Backend.controller;


import com.backend.Backend.Service.HorarioService;
import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {
    @Autowired
    HorarioRepository horarioRepository;
    @PostMapping("/upload")
    public Horario nuevoHorario(@RequestBody Horario horario){
       //return horarioService.create(horario);
        System.out.println(horario.getHorarioAsignaturas());
        horarioRepository.save(horario);
        return null;
    }
    @GetMapping("/getHorario")
    public List<HorarioAsignatura> getHorario(@RequestParam("planId") Long planId){
        //return horarioService.getHorariosPlan(planId);
        return null;
    }
}
