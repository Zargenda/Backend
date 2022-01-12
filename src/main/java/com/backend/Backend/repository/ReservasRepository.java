package com.backend.Backend.repository;

import com.backend.Backend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservasRepository extends JpaRepository<Reserva, Long> {
    @Query(value = "SELECT a.* FROM Reserva a WHERE " +
            "a.fecha = :fecha AND  a.aula_Id = :aulaId",nativeQuery = true)
    public List<Reserva> getReservasDia(@Param("fecha") String fecha, @Param("aulaId")String aulaId);


}
