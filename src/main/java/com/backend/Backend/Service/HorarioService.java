package com.backend.Backend.Service;


import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.HorarioAsignaturaRepository;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HorarioService {
    @Autowired
    private HorarioAsignaturaRepository horarioAsignaturaRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    public Map<Horario,List<HorarioAsignatura>> create (Horario horario,List<HorarioAsignatura> lista) {
        //Guarda horario
        Map<Horario,List<HorarioAsignatura>> result = new HashMap<>();
        Horario horario1 =  horarioRepository.save(horario);
        for(HorarioAsignatura asign : lista){
            asign.setIdPadre(horario1.getHorarioId());
        }
        lista = horarioAsignaturaRepository.saveAll(lista);
        result.put(horario,lista);
        return result;
    }

    public List<HorarioAsignatura> getAllHorarios (){
        return horarioAsignaturaRepository.findAll();
    }

    public List <HorarioAsignatura> getHorariosPlan(Long idPlan){
        return horarioAsignaturaRepository.horarioPlan(idPlan);
    }
    public List<HorarioAsignatura> getHorario (String nombre_plan,String semestre,int curso, int grupo){
        return horarioAsignaturaRepository.getHorarioA(nombre_plan, semestre, curso, grupo);
    }

    public void delete (HorarioAsignatura hAsignatura) {
        horarioAsignaturaRepository.delete(hAsignatura);
    }

    public Optional<HorarioAsignatura> findById (Long id) {
        return horarioAsignaturaRepository.findById(id);
    }
}
