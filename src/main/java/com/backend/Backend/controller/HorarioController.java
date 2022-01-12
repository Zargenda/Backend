package com.backend.Backend.controller;


import com.backend.Backend.Service.HorarioService;
import com.backend.Backend.model.Conflicto;
import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.ConflictoRepository;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/horarios")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class HorarioController {
    @Autowired
    HorarioService horarioService;
    @Autowired
    ConflictoRepository conflictoRepository;

    @PostMapping("/uploadHorario")
    public Map<Horario,List<HorarioAsignatura>> nuevoHorario(@RequestBody  Map<Horario,List<HorarioAsignatura>> body) {
       //return horarioService.create(horario);

        Map.Entry<Horario,List<HorarioAsignatura>> entry = body.entrySet().iterator().next();
        Horario key = entry.getKey();
        List<HorarioAsignatura> value = entry.getValue();
        return  horarioService.create(key,value);
    }
    @GetMapping("/getHorario")
    public List<HorarioAsignatura> getHorario(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
                                              @RequestParam("curso") int curso, @RequestParam("grupo") int grupo ){
        return horarioService.getHorario(nombrePlan, semestre, curso, grupo);
        
    }

    @PostMapping("/uploadHorarioA")
    public Map<Horario,List<HorarioAsignatura>> prueba(@RequestBody  JsonHorario json) {
        if(json.id != 0){
            Map<Horario,List<HorarioAsignatura>> result = new HashMap<>();
            Horario horario = new Horario(json.id,json.curso,json.semestre,json.grupo, json.nombrePlan);
            result.put(horario,horarioService.onUpdate(json.horarioAsignaturas, json.id));
            return result;
        }
        else {
            Horario horario = new Horario(0L, json.curso, json.semestre, json.grupo, json.nombrePlan);
            System.out.println(json.toString());
            System.out.println(horario);
            return horarioService.create(horario, json.horarioAsignaturas);
        }

    }

    @GetMapping("/getConflictos")
    public List<Conflicto> getConflictoId(@RequestParam("id") Long horarioId){
        return conflictoRepository.getConflictosByHorarioId(horarioId);
    }


}

class JsonHorario{
    Long id;
    int curso;
    String semestre;
    int grupo;
    String nombrePlan;

    List<HorarioAsignatura> horarioAsignaturas = new ArrayList<>();

    public JsonHorario(Long id, int curso,String nombrePlan, String semestre, int grupo, List<HorarioAsignatura> horarioAsignaturas) {
        this.id = id;
        this.curso = curso;
        this.semestre = semestre;
        this.grupo = grupo;
        this.nombrePlan = nombrePlan;
        this.horarioAsignaturas = horarioAsignaturas;
    }

    @Override
    public String toString() {
        return "JsonHorario{" +
                "id=" + id +
                ", curso=" + curso +
                ", semestre='" + semestre + '\'' +
                ", grupo=" + grupo +
                ", nombrePlan='" + nombrePlan + '\'' +
                ", horarioAsignaturas=" + horarioAsignaturas +
                '}';
    }
}
