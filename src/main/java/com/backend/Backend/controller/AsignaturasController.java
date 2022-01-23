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

	//Endpoint del API rest utilizado para subir el listado207 y guardarlo en la BD
	@PostMapping("/upload")
	public List<Asignatura>  upload(@RequestParam("file") MultipartFile file){
		asignaturaRepository.deleteAll();
		return asignaturaRepository.saveAll(ExcelReader.leerAsignaturas(file)) ;
	}


	//Endpoint del API rest que borra una asignatura de la Db dado su id
	@PostMapping("/delete")
	public String  deleteAsignatura(@RequestParam("id") Long id){
		asignaturaRepository.deleteById(id);
		return "EXITO";
	}

	//Endpoint del API rest que modifica una asignatura con id = id
	@PostMapping("/update")
	public String updateAsignatura(@RequestParam("id")Long id, @RequestParam("semester") String semestre, @RequestParam("grade") int curso,
								 @RequestParam("name") String nombre, @RequestParam("group") int grupo){
		asignaturaRepository.updateAsignatura(id,semestre,curso,nombre,grupo);
		return "EXITO";
	}

	//Endpoint del API rest que devuelve todas las asignaturas almacenadas en la DB
	@GetMapping("/getAsignaturas")
	public List<Asignatura> getAsignaturas(){
		return asignaturaRepository.findAll();
	}

	//Endpoint del API rest que devuelve todos los planes almacenados en la DB
	@GetMapping("/getAreas")
	public List<String> getAreas(){
		return asignaturaRepository.getPlanes();
	}

	//Endpoint del API rest que devuelve todos los semestres existentes dado un plan
	@GetMapping("/getSemestres")
	public Map<String,List<String>> getSemestres(@RequestParam("nombrePlan") String nombrePlan ){
		Map<String,List<String>> result = new HashMap<String, List<String>>();
		result.put("Semester",asignaturaRepository.getSemestres(nombrePlan));
		result.put("Grade",asignaturaRepository.getCursos(nombrePlan));
		return result;
	}
	//Endpoint del API rest que devuelve todos los grupos dado el plan , el semestre y el curso
	@GetMapping("/getGroups")
	public List<String> getGrupos(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
								   @RequestParam("curso") String curso){
		return asignaturaRepository.getGrupos(nombrePlan,semestre, curso);
	}
	//Endpoint del API rest que devuelve todas las asignaturas posibles dado el plan , el semestre y el curso
    @GetMapping("/getSubjects")
	public List<String> getAsignaturas(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
									   @RequestParam("curso") String curso ){
			return asignaturaRepository.getAsignaturas(nombrePlan,semestre,curso);
	}

	//Endpoint del API rest que devuelve todas las asignaturas ygrupos posibles dado el plan, el semestre y el curso
	@GetMapping("/getGroupsAndSubjects")
	public Map<String,List<String>> getGroupsAndSubjects(@RequestParam("nombrePlan") String nombrePlan,@RequestParam("semestre") String semestre,
														 @RequestParam("curso") String curso){
		Map<String,List<String>> result = new HashMap<String, List<String>>();
		result.put("groups",asignaturaRepository.getGrupos(nombrePlan,semestre,curso));
		result.put("subjects",asignaturaRepository.getAsignaturas(nombrePlan,semestre,curso));
		return result;

	}


}
