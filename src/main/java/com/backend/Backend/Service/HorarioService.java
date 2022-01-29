package com.backend.Backend.Service;
/*
Service de horario el cual controla toda la lógica del negocio de la creación,modificación y eliminacion,esta usa tanto
los repositorios de Reservas y Conflictos
 */



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


    //Dado una horario y una lista de horas con sus asignaturas crea una relacion entre la lista y el horario
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
    //Borra la anterior lista de horas de las asignaturas y guarda una nueva
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
    //Obtiene todos los horarios
    public List<HorarioAsignatura> getAllHorarios (){
        return horarioAsignaturaRepository.findAll();
    }
    //Obtiene un horario dado su id
    public List <HorarioAsignatura> getHorariosPlan(Long idPlan){
        return horarioAsignaturaRepository.horarioPlan(idPlan);
    }
    //Obtiene un horario dado su nombre del plan,semestre,curso y grupo
    public List<HorarioAsignatura> getHorario (String nombre_plan,String semestre,int curso, int grupo){
        return horarioAsignaturaRepository.getHorarioA(nombre_plan, semestre, curso, grupo);
    }
    //Borra una hora de una asignatura
    public void delete (HorarioAsignatura hAsignatura) {
        horarioAsignaturaRepository.delete(hAsignatura);
    }
    //Obtiene una hora de una asignatura si existe
    public Optional<HorarioAsignatura> findById (Long id) {
        return horarioAsignaturaRepository.findById(id);
    }

    //Se encarga de gestionar las reservas de las aulas dado un horario ademas de comprobar conflictos
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
    //btiene un horario dado su nombre del plan,semestre,curso y grupo
    public Horario getIdAux( String nombre_plan, String semestre,int curso,int grupo){
        return horarioRepository.getHorarioByNombrePlanAndCursoAndSemestreAndGrupo(nombre_plan,curso,semestre,grupo);
    }


}
