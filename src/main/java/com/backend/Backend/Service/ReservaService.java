package com.backend.Backend.Service;

import com.backend.Backend.model.Reserva;
import com.backend.Backend.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {
    @Autowired
    private ReservasRepository reservaRepository;

    public Reserva create (Reserva reserva) {
        comprobarconflictos(reserva);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> getAllReservas (){
        return reservaRepository.findAll();
    }

    public void delete (Reserva reserva) {
        reservaRepository.delete(reserva);
    }

    public Optional<Reserva> findById (Long id) {
        return reservaRepository.findById(id);
    }

    private boolean comprobarconflictos (Reserva reserva){
        String fecha = reserva.getFecha();
        boolean conflicto = false;
        List<Reserva> reservasDB = reservaRepository.getReservasDia(fecha, reserva.getAulaId());
        for (Reserva aux : reservasDB){
            if ((coinciden(aux.getHorainicio(),reserva.getHorainicio(),aux.getHorafin(),reserva.getHorafin())||(coinciden(reserva.getHorainicio(),aux.getHorainicio(),reserva.getHorafin(),aux.getHorafin())))){

                conflicto = true;
            }
        }
        return conflicto;
    }

    private Date limpiarTiempo(Date test) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String tm =sdf.format(test);
        Date horafinal = sdf.parse(tm);
        return horafinal;
    }

    private boolean coinciden (Date refini, Date ini, Date reffin, Date fin){
        if (((ini.after(refini))&&(ini.before(reffin)))||((fin.after(refini))&&(fin.before(fin)))){
            return true;
        }
        return false;
    }
}
