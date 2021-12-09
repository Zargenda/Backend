package com.backend.Backend.repository;

import com.backend.Backend.model.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	public Asignatura create (Asignatura asignatura) {
		return asignaturaRepository.save(asignatura);
	}
	
	public List<Asignatura> getAllAsignaturas (){
		return asignaturaRepository.findAll();
	}
	
	public void delete (Asignatura asignatura) {
		asignaturaRepository.delete(asignatura);
	}
	
	public Optional<Asignatura> findById (Long id) {
		return asignaturaRepository.findById(id);
	}

}
