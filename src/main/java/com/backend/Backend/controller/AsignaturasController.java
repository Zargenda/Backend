package com.backend.Backend.controller;

import com.backend.Backend.Service.AsignaturaService;
import com.backend.Backend.Utils.ExcelReader;
import com.backend.Backend.model.Asignatura;
import com.backend.Backend.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/asignaturas")
public class AsignaturasController {

	@Autowired
	private AsignaturaRepository asignaturaRepository;


	@PostMapping("/upload")
	public String  prueba2(@RequestParam("file") MultipartFile file){
		//ExcelReader.leerAsignaturas(file);
		return "EXITO";
	}

	@GetMapping("/getAsignaturas")
	public List<Asignatura> getAsignaturas(){
		return asignaturaRepository.findAll();
	}

	@GetMapping("/getAreas")
	public List<String> getAreas(){
		return asignaturaRepository.getPlanes();
	}
	@GetMapping("/getSemestres")
	public Map<String,List<String>> getSemestres(@RequestParam("nombrePlan") String nombrePlan ){
		Map<String,List<String>> result = new HashMap<String, List<String>>();
		result.put("Semester",asignaturaRepository.getSemestres(nombrePlan));
		result.put("Grade",asignaturaRepository.getCursos(nombrePlan));
		return result;
	}
	public List<Integer> getGrupos(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
								   @RequestParam("curso") int curso){
		return asignaturaRepository.getGrupos(nombrePlan,semestre,curso);
	}

	public List<String> getAsignaturas(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
									   @RequestParam("curso") int curso,@RequestParam("grupo") int grupo  ){
			return asignaturaRepository.getAsignaturas(nombrePlan,semestre,curso,grupo);
	}


}
