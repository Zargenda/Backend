package com.backend.Backend.repository;
import com.backend.Backend.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AulaRepository extends  JpaRepository<Aula,Long> {
    @Query(value = "SELECT a.nombre FROM Aula a  WHERE a.edificio = :edificio",  nativeQuery = true)
    List<String> getAulasbyEdificio(@Param("edificio") int edificio);
}
