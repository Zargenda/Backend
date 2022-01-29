package com.backend.Backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity 
public class Calendario {
	@Id
	private String date;


	private String type;

	private String day;

	private String week;

	private String comment;
	
	public Calendario() {}

	public Calendario(String date, String type, String day, String week, String comment) {
		this.date = date;
		this.type = type;
		this.day = day;
		this.week = week;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Calendario{" +
				"date='" + date + '\'' +
				", type='" + type + '\'' +
				", day='" + day + '\'' +
				", week='" + week + '\'' +
				", comment='" + comment + '\'' +
				'}';
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
