package com.backend.Backend.Service;

import com.backend.Backend.model.Conflicto;
import com.backend.Backend.model.Reserva;
import com.backend.Backend.repository.ConflictoRepository;
import com.backend.Backend.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {
    @Autowired
    private ReservasRepository reservaRepository;
    @Autowired
    private ConflictoRepository conflictoRepository;

    //Funcion que dad unha reserva la almacena el la DB y llama a generarConflictos
    public Reserva create (Reserva reserva) {
        generarConflictos(reserva);
        return reservaRepository.save(reserva);
    }

    //Funcion que devuelve todas las reservas
    public List<Reserva> getAllReservas (){
        return reservaRepository.findAll();
    }

    //Funcion que dada una reserva la elimina de la DB
    public void delete (Reserva reserva) {
        reservaRepository.delete(reserva);
    }

    //Funcion que devuelve una reserva cuya id sea la pasada como parametro
    public Optional<Reserva> findById (Long id) {
        return reservaRepository.findById(id);
    }

    //Funcion que comprueba si existe comflicto y en caso afirmativo lo registra en la DV
    private void generarConflictos (Reserva reserva){
        String fecha = reserva.getFecha();
        List<Conflicto> conflictos = new ArrayList<Conflicto>();
        List<Reserva> reservasDB = reservaRepository.getReservasDia(fecha, reserva.getAulaId());
        for (Reserva aux : reservasDB){
            if ((coinciden(aux.getHorainicio(),reserva.getHorainicio(),aux.getHorafin(),reserva.getHorafin())||(coinciden(reserva.getHorainicio(),aux.getHorainicio(),reserva.getHorafin(),aux.getHorafin())))){
                String description = makeDescription(reserva.getAulaId(),reserva.getNombre_Asignatura(),aux.getNombre_Asignatura(),reserva.getFecha());
                conflictos.add(new Conflicto(reserva.getIdHorario(),description));

            }
        }
        conflictoRepository.saveAll(conflictos);

    }


    private boolean coinciden (Date refini, Date ini, Date reffin, Date fin){
       if(((ini.compareTo(refini)>=0)&&(ini.compareTo(reffin)<0))||((fin.compareTo(refini)>0)&&(fin.compareTo(reffin)<=0))){
            System.out.println("COINCIDE");
            return true;
        }
        return false;
    }

    public static String makeDescription( String aula,String asignaturaA,String asignaturaB,String fechaA){
        return " Existe conflicto con el  " + aula +   " entre "  + asignaturaA + " y " + asignaturaB + " en " +fechaA;
    }
    //Exite conflicto con el AULA  el DIA por AsignaturaA[horaini,horafin] y por AsignaturaB[horaini,horafin]

}
