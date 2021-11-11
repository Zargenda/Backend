package model;


import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter @Setter @NoArgsConstructor
@Table(name = "Asignatura")
public class Asignatura {
	
	private @Id Long id;
	private String nombre;
	/*private Long codArea;
	private Long codPlan;
	private int grupo; 
	private int curso;
	private int semestre;
	private String prueba;
	*/
	public Asignatura(Long id) {
		this.id = id;
	}

	
	
	//Completar cuando se hagan las tablas
}

