package com.backend.Backend.controller;

import com.backend.Backend.model.Asignatura;
import com.backend.Backend.repository.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AsignaturasController {

	@Autowired
	private AsignaturaService asignaturaService;

	@GetMapping("/prueba")
	public Asignatura prueba() {
		Asignatura asing = new Asignatura(10L);
		asignaturaService.create(asing);
		return asing;
	}


}
