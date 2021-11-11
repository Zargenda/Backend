package repository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Asignatura;

@Service
public class AsignaturaService {
	
	@Autowired
	private AsignaturaRepository personaResporitory;
	
	
	public Asignatura create (Asignatura persona) {
		return personaResporitory.save(persona);
	}
	
	public List<Asignatura> getAllPersonas (){
		return personaResporitory.findAll();
	}
	
	public void delete (Asignatura persona) {
		personaResporitory.delete(persona);
	}
	
	public Optional<Asignatura> findById (Long id) {
		return personaResporitory.findById(id);
	}
	

}
