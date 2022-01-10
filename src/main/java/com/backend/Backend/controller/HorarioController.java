package com.backend.Backend.controller;


import com.backend.Backend.Service.HorarioService;
import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
