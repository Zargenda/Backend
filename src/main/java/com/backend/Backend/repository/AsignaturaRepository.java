package com.backend.Backend.repository;

import com.backend.Backend.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/*/
 * 	Implementación de las peticiones a base de datos.
 */
@Transactional
public interface AsignaturaRepository  extends JpaRepository<Asignatura, Long>{
	@Query(value = "SELECT DISTINCT a.nombre_plan FROM Asignatura a ",  nativeQuery = true)
	public  List<String> getPlanes();

	@Query(value = "SELECT DISTINCT a.semestre  FROM Asignatura a WHERE a.nombre_plan = :nombre_plan ",  nativeQuery = true)
	public List<String> getSemestres(@Param("nombre_plan")String nombre_plan);

	@Query(value = "SELECT DISTINCT a.curso FROM Asignatura a WHERE a.nombre_plan = :nombre_plan ORDER BY a.curso ",nativeQuery = true)
	public List<String> getCursos(@Param("nombre_plan")String nombre_plan);

	@Query(value = "SELECT DISTINCT a.grupo FROM Asignatura a WHERE " +
			"a.nombre_plan = :nombre_plan AND  a.curso = :curso  AND  a.semestre = :semestre   ",nativeQuery = true)
	public List<String> getGrupos(@Param("nombre_plan") String nombre_plan, @Param("semestre")String semestre,
								   @Param("curso") String curso );

	@Query(value = "SELECT DISTINCT a.nombre FROM Asignatura a WHERE " +
			"a.nombre_plan = :nombre_plan AND  a.curso = :curso  AND  a.semestre = :semestre ",nativeQuery = true)
	public List<String> getAsignaturas(@Param("nombre_plan") String nombre_plan, @Param("semestre")String semestre,
								   @Param("curso")String curso);
	public void deleteAll();

	@Modifying
	@Query(value = "UPDATE Asignatura  Set nombre = :nombre, semestre = :semestre ,curso = :curso, " +
			"grupo = :grupo WHERE id = :id  " ,nativeQuery = true)
	public void updateAsignatura(@Param("id")Long id, @Param("semestre") String Semestre, @Param("curso") int curso,
	@Param("nombre") String nombre, @Param("grupo") int grupo);

}
