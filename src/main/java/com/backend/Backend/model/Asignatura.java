package com.backend.Backend.model;


import javax.persistence.*;

import lombok.*;

@Entity 
@Getter @Setter @NoArgsConstructor @ToString
@Table(name = "Asignatura")
public class Asignatura {
	
	private @Id Long id;
	private String nombre;
	private Long codArea;
	private Long codPlan;
	private int grupo; 
	private int curso;
	private String semestre;

	public Asignatura(Long id) {
		this.id = id;
	}

	public Asignatura(Long id, String nombre, Long codArea, Long codPlan, int grupo, int curso, String semestre) {
		this.id = id;
		this.nombre = nombre;
		this.codArea = codArea;
		this.codPlan = codPlan;
		this.grupo = grupo;
		this.curso = curso;
		this.semestre = semestre;
	}
	//Completar cuando se hagan las tablas
}

