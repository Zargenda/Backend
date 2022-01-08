package com.backend.Backend.controller;

import com.backend.Backend.Service.ReservaService;

import com.backend.Backend.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    @PostMapping("/upload")
    public Reserva nuevoHorario(@RequestParam("reserva") Reserva reserva){
        return reservaService.create(reserva);
    }
    @GetMapping("/reservas")
    public List<Reserva> getReservas(){
        return reservaService.getAllReservas();
    }
}
