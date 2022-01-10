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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
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
	@GetMapping("/getGroups")
	public List<String> getGrupos(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
								   @RequestParam("curso") String curso){
		return asignaturaRepository.getGrupos(nombrePlan,semestre, curso);
	}
    @GetMapping("/getSubjects")
	public List<String> getAsignaturas(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
									   @RequestParam("curso") String curso ){
			return asignaturaRepository.getAsignaturas(nombrePlan,semestre,curso);
	}

	@GetMapping("/getGroupsAndSubjects")
	public Map<String,List<String>> getGroupsAndSubjects(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
														 @RequestParam("curso") String curso){
		Map<String,List<String>> result = new HashMap<String, List<String>>();
		result.put("groups",asignaturaRepository.getGrupos(nombrePlan,semestre,curso));
		result.put("subjects",asignaturaRepository.getAsignaturas(nombrePlan,semestre,curso));
		return result;

	}


}
