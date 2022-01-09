package com.backend.Backend.controller;

import com.backend.Backend.Service.AsignaturaService;
import com.backend.Backend.Utils.ExcelReader;
import com.backend.Backend.model.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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

	@PostMapping("/upload")
	public String  prueba2(@RequestParam("file") MultipartFile file){
		ExcelReader.leerAsignaturas2(file);
		return "EXITO";
	}


}
