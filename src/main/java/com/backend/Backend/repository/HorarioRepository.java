package com.backend.Backend.repository;

import com.backend.Backend.model.Conflicto;
import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    @Query(value = "SELECT a.* FROM horario h ,horario_asignatura a WHERE " +
            "h.nombre_plan = :nombre_plan AND  h.curso = :curso  AND  h.semestre = :semestre AND " +
            "a.id_padre = h.horario_id AND h.grupo = :grupo   ",nativeQuery = true)
    public List<HorarioAsignatura> getHorarioA(@Param("nombre_plan") String nombre_plan, @Param("semestre")String semestre,
                                               @Param("curso") int curso, @Param("grupo") int grupo );
}
