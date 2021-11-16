package com.backend.Backend.repository;

import com.backend.Backend.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

/*/
 * 	Implementación de las peticiones a base de datos.
 */
public interface AsignaturaRepository  extends JpaRepository<Asignatura, Long>{
	
}
