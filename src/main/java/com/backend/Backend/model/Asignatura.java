package com.backend.Backend.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Getter @Setter @NoArgsConstructor @ToString
@Table(name = "Asignatura")
public class Asignatura {
	
	private @Id Long id;
	private String nombre;
	private Long codArea;
	private String nombreArea;
	private Long codPlan;
	private int grupo; 
	private int curso;
	private String semestre;
	private int horaSemanales;

	public Asignatura(Long id) {
		this.id = id;
	}

	public Asignatura(Long id, String nombre, Long codArea, Long codPlan, int grupo, int curso, String semestre
			,int horaSemanales,String nombreArea) {
		this.id = id;
		this.nombre = nombre;
		this.codArea = codArea;
		this.codPlan = codPlan;
		this.grupo = grupo;
		this.curso = curso;
		this.semestre = semestre;
		this.horaSemanales = horaSemanales;
		this.nombreArea = nombreArea;
	}
	//Completar cuando se hagan las tablas
}

