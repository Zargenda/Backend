package com.backend.Backend.repository;
import java.util.List;
import java.util.Optional;

import com.backend.Backend.model.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	
	public Asignatura create (Asignatura asignatura) {
		return asignaturaRepository.save(asignatura);
	}
	
	public List<Asignatura> getAllPersonas (){
		return asignaturaRepository.findAll();
	}
	
	public void delete (Asignatura persona) {
		asignaturaRepository.delete(persona);
	}
	
	public Optional<Asignatura> findById (Long id) {
		return asignaturaRepository.findById(id);
	}
	

}
