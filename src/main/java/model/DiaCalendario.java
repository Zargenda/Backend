package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter @Setter @NoArgsConstructor @Data
public class DiaCalendario {
	private @Id Long id;
	private int type;
	private Date fechaInicio;
	private Date fechaFinal;
	private int diaSemana;
	
	public DiaCalendario(Long id) {
		this.id = id;
	}
	
}
