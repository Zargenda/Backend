package com.backend.Backend.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
public class Calendario {
	@Id
	private Date date;


	private String type;

	private String day;

	private String week;

	private String comment;
	
	public Calendario(Date date) {
		this.date = date;
	}
	
}
