package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Asignatura;

/*/
 * 	Implementación de las peticiones a base de datos.
 */
public interface AsignaturaRepository  extends JpaRepository<Asignatura, Long>{
	
}
