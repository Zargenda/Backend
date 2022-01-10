package com.backend.Backend.repository;

import com.backend.Backend.model.Conflicto;
import com.backend.Backend.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
