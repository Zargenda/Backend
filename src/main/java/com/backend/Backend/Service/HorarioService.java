package com.backend.Backend.Service;


import com.backend.Backend.model.Asignatura;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;


    public HorarioAsignatura create (HorarioAsignatura hAsignatura) {
        if(hAsignatura.getAulaId() != 0 ){
            //Comprobar conflictos
        }

        return horarioRepository.save(hAsignatura);
    }

    public List<HorarioAsignatura> getAllHorarios (){
        return horarioRepository.findAll();
    }
    public List <HorarioAsignatura> getHorariosPlan(Long idPlan){
        horarioRepository.
    }
    public void delete (HorarioAsignatura hAsignatura) {
        horarioRepository.delete(hAsignatura);
    }

    public Optional<HorarioAsignatura> findById (Long id) {
        return horarioRepository.findById(id);
    }
}
