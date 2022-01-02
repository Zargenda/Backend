package com.backend.Backend.repository;

import com.backend.Backend.model.Asignatura;
import com.backend.Backend.model.HorarioAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorarioRepository  extends JpaRepository<HorarioAsignatura, Long> {
    @Query (value = "SELECT * FROM HorarioAsignatura h WHERE h.planId = :idPlan",  nativeQuery = true)
    public List<HorarioAsignatura> horarioPlan(@Param("idPlan") Long plan );
}
