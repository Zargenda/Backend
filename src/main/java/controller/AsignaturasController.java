package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Asignatura;

@RestController
public class AsignaturasController {
	
	@RequestMapping(value = "/") 
	public String index() {
		return "index"; 
	}
	

	@RequestMapping(value = "/prueba2") 
	public Asignatura prueba2() {
		Asignatura asing = new Asignatura(10L);
		return asing; 
	}
	@RequestMapping(value = "/prueba2") 
	public Asignatura prueba3() {
		Asignatura asing = new Asignatura(10L);
		return asing; 
	}
	@RequestMapping(value = "/prueba2") 
	public Asignatura prueba4() {
		Asignatura asing = new Asignatura(10L);
		return asing; 
	}
	@RequestMapping(value = "/prueba2") 
	public Asignatura prueba5() {
		Asignatura asing = new Asignatura(10L);
		return asing; 
	}
}
