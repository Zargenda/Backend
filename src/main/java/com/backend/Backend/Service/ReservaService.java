package com.backend.Backend.Service;

import com.backend.Backend.model.Reserva;
import com.backend.Backend.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {
    @Autowired
    private ReservasRepository reservaRepository;

    public Reserva create (Reserva reserva) {
        //Comprobar conflictos
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
}
