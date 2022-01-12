package com.backend.Backend.Service;


import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.model.Reserva;
import com.backend.Backend.repository.ConflictoRepository;
import com.backend.Backend.repository.HorarioAsignaturaRepository;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class HorarioService {
    @Autowired
    private HorarioAsignaturaRepository horarioAsignaturaRepository;
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ConflictoRepository conflictoRepository;



    public Map<Horario,List<HorarioAsignatura>> create (Horario horario,List<HorarioAsignatura> lista) {
        //Guarda horario
        Map<Horario,List<HorarioAsignatura>> result = new HashMap<>();
        Horario horario1 =  horarioRepository.save(horario);
        for(HorarioAsignatura asign : lista){
            asign.setIdPadre(horario1.getHorarioId());
            reservaService.create(asignaturaReserva(asign) );
        }
        lista = horarioAsignaturaRepository.saveAll(lista);
        result.put(horario,lista);
        return result;
    }

    public List<HorarioAsignatura> onUpdate(List<HorarioAsignatura> asigns,Long idPadre){
           if(horarioAsignaturaRepository.deleteHorarioAsignaturaByIdPadre(idPadre) > 0 ) {
               for(HorarioAsignatura asign : asigns){
                   conflictoRepository.deleteConflictoByHorarioId(asign.getIdPadre());
                   reservaService.create(asignaturaReserva(asign) );
                   //limpiar conflictos
               }


               return horarioAsignaturaRepository.saveAll(asigns);
           }
          return null;
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

    private Reserva asignaturaReserva(HorarioAsignatura hAsing){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tm =sdf.format(hAsing.getStartTime());
        String dia= "";
        switch(tm){
            case "2021-09-13": dia = "lunes";
            break;
            case "2021-09-14": dia = "martes";
                break;
            case "2021-09-15": dia = "miércoles";
                break;
            case "2021-09-16": dia = "jueves";
                break;
            case "2021-09-17": dia = "viernes";
                break;

        }

         return new Reserva(hAsing.getIdPadre(),hAsing.getSubject(),dia, hAsing.getStartTime(),hAsing.getEndTime(),hAsing.getDescription()
                 ,hAsing.getFrecuency());
    }

    public Long getIdAux( String nombre_plan, String semestre,int curso,int grupo){
        return horarioRepository.getHorarioId(nombre_plan,semestre,curso,grupo);
    }


}
