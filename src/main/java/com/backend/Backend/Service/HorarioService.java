package com.backend.Backend.Service;


import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.AsignaturaRepository;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private AsignaturaService asignaturaService;

    public HorarioAsignatura create (HorarioAsignatura hAsignatura) {
        /*int weeklyHours = asignaturaService.getWeeklyHours(hAsignatura.getAsignaturaId());
        if(hAsignatura.getAulaId() != 0 ){
            //Comprobar conflictos con reserva de aulas
        }
        if(weeklyHours < hAsignatura.getHora().size()){
            System.out.println("DEMASIADAS HORAS");
        }
        */
        return horarioRepository.save(hAsignatura);
    }

    public List<HorarioAsignatura> getAllHorarios (){
        return horarioRepository.findAll();
    }

    public List <HorarioAsignatura> getHorariosPlan(Long idPlan){
        return horarioRepository.horarioPlan(idPlan);
    }
    public List <HorarioAsignatura> createHorario(List<HorarioAsignatura> horarioPlan){
        return horarioRepository.saveAll(horarioPlan);
    }

    public void delete (HorarioAsignatura hAsignatura) {
        horarioRepository.delete(hAsignatura);
    }

    public Optional<HorarioAsignatura> findById (Long id) {
        return horarioRepository.findById(id);
    }
}
