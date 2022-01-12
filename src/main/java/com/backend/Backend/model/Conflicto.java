package com.backend.Backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Conflicto")
public class Conflicto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long horarioId;
    private String descripcion;//Exite conflicto con el AULA  el DIA por AsignaturaA[horaini,horafin] y por AsignaturaB[horaini,horafin]

    public Conflicto(Long horarioId, String descripcion) {
        this.horarioId = horarioId;
        this.descripcion = descripcion;
    }
}
