package com.backend.Backend.controller;


import com.backend.Backend.Service.HorarioService;
import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/horarios")
public class HorarioController {
    @Autowired
    HorarioService horarioService;
    @PostMapping("/uploadHorario")
    public Map<Horario,List<HorarioAsignatura>> nuevoHorario(@RequestBody  Map<Horario,List<HorarioAsignatura>> body) {
       //return horarioService.create(horario);

        Map.Entry<Horario,List<HorarioAsignatura>> entry = body.entrySet().iterator().next();
        Horario key = entry.getKey();
        List<HorarioAsignatura> value = entry.getValue();
        return  horarioService.create(key,value);
    }
    @GetMapping("/getHorario")
    public List<HorarioAsignatura> getHorario(@RequestParam("planId") Long planId){
        //return horarioService.getHorariosPlan(planId);
        return null;
    }

    @PostMapping("/uploadHorarioA")
    public Map<Horario,List<HorarioAsignatura>> prueba(@RequestBody  JsonHorario json) {
        //return horarioService.create(horario);
        Horario horario = new Horario(0L,json.curso,json.semestre,json.grupo);
        System.out.println(horario);
        System.out.println(json.horarioAsignaturas);
       // return  horarioService.create(horario,json.horarioAsignaturas);
        return null;
    }

}

class JsonHorario{
    Long id;
    int curso;
    String semestre;
    int grupo;
    List<HorarioAsignatura> horarioAsignaturas = new ArrayList<>();

    public JsonHorario(Long id, int curso, String semestre, int grupo, List<HorarioAsignatura> horarioAsignaturas) {
        this.id = id;
        this.curso = curso;
        this.semestre = semestre;
        this.grupo = grupo;
        this.horarioAsignaturas = horarioAsignaturas;
    }
}
