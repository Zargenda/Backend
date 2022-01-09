package com.backend.Backend.repository;

import com.backend.Backend.model.Conflictos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConflictoRepository extends JpaRepository<Conflictos, Long> {
}
