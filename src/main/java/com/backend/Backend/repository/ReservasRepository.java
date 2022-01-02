package com.backend.Backend.repository;

import com.backend.Backend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface ReservasRepository extends JpaRepository<Reserva, Long> {

}
