package com.backend.Backend.repository;

import com.backend.Backend.model.Asignatura;
import com.backend.Backend.model.HorarioAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*/
 * 	Implementación de las peticiones a base de datos.
 */
public interface AsignaturaRepository  extends JpaRepository<Asignatura, Long>{
	@Query(value = "SELECT DISTINCT  FROM Asignatura a WHERE h.planId = :idPlan",  nativeQuery = true)
	public List<HorarioAsignatura> horarioPlan(@Param("idPlan") Long plan );

	@Query(value = "SELECT DISTINCT   FROM Asignatura ",  nativeQuery = true)
	public List<String> areasDisponibles();
	
}
