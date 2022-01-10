package com.backend.Backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Horario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long horarioId;
    private int curso;
    private String semestre;
    private String nombrePlan;
    private int grupo;

    public Horario(Long horarioId, int curso, String semestre, int grupo, String nombrePlan) {
        this.horarioId = horarioId;
        this.curso = curso;
        this.semestre = semestre;
        this.grupo = grupo;
        this.nombrePlan = nombrePlan;
    }
}
