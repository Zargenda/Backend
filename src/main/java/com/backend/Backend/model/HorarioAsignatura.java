package com.backend.Backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Horario")
public class HorarioAsignatura {
    private @Id Long id;
    private Long asignaturaId ; //  Asignatura
    private Long planId; //El plan al que pertenece
    private int hora; // horario de la universidad
    private int diaSemana;// Dias de la semana
    private String tipo; //Practicas teoria problemas
    private Long aulaId; //Opcional
}
