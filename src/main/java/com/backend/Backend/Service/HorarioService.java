package com.backend.Backend.Service;


import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.HorarioAsignaturaRepository;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
    @Autowired
    private HorarioAsignaturaRepository horarioAsignaturaRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    public Horario create (Horario horario) {
        //Guarda horario
        Horario horario1 =  horarioRepository.save(horario);
        for(HorarioAsignatura asign : horario1.getHorarioAsignaturas()){

        }
        List<HorarioAsignatura> lista = horarioAsignaturaRepository.saveAll(horario.getHorarioAsignaturas());
        horario1.setHorarioAsignaturas(lista);
        return horario1;
    }

    public List<HorarioAsignatura> getAllHorarios (){
        return horarioAsignaturaRepository.findAll();
    }

    public List <HorarioAsignatura> getHorariosPlan(Long idPlan){
        return horarioAsignaturaRepository.horarioPlan(idPlan);
    }
    public List <HorarioAsignatura> createHorario(List<HorarioAsignatura> horarioPlan){
        return horarioAsignaturaRepository.saveAll(horarioPlan);
    }

    public void delete (HorarioAsignatura hAsignatura) {
        horarioAsignaturaRepository.delete(hAsignatura);
    }

    public Optional<HorarioAsignatura> findById (Long id) {
        return horarioAsignaturaRepository.findById(id);
    }
}
