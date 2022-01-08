package com.backend.Backend.controller;

import com.backend.Backend.Service.AsignaturaService;
import com.backend.Backend.Utils.ExcelReader;
import com.backend.Backend.model.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/asignaturas")
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
		ExcelReader.leerAsignaturas(file);
		return "EXITO";
	}


}
