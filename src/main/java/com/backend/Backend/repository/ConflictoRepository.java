package com.backend.Backend.repository;

import com.backend.Backend.model.Conflicto;
import com.backend.Backend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConflictoRepository extends JpaRepository<Conflicto, Long> {
    @Query(value = "SELECT DISTINCT a FROM Conflicto a WHERE " +
            "a.aula = :aula",nativeQuery = true)
    public List<Conflicto> getConflictoAula(@Param("aula") String aula);
    public List<Conflicto> getConflictosByHorarioId(Long id);
    public int deleteConflictoByHorarioId(Long id);
}
