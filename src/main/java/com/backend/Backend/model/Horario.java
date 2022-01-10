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
    private int grupo;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "horario")
    private List<HorarioAsignatura> horarioAsignaturas ;
}
