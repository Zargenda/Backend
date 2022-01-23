package com.backend.Backend.Service;

import com.backend.Backend.model.Asignatura;
import com.backend.Backend.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService  {
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;

	//Dada una asignatura la almacena el la DB
	public Asignatura create (Asignatura asignatura) {
		return asignaturaRepository.save(asignatura);
	}

	//Devuelve todas las asignaturas almacenadas
	public List<Asignatura> getAllAsignaturas (){
		return asignaturaRepository.findAll();
	}

	//Dada una asignatura la borra de la DB
	public void delete (Asignatura asignatura) {
		asignaturaRepository.delete(asignatura);
	}

	//Dada un id devuelve la asignaturaw cuyo id sea el id pasado
	public Optional<Asignatura> findById (Long id) {
		return asignaturaRepository.findById(id);
	}

	//Dada un id devuelve el numero de horas referente a esa asignatura
	public int getWeeklyHours (Long id){
		return asignaturaRepository.findById(id).get().getHoraSemanales();
	}

}
