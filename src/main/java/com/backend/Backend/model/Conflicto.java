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
@Table(name = "Conflicto")
public class Conflicto {
    private @Id Long id;
    private String descripcion;//Este AULA  esta reservada por AsignaturaA y por Asignatura B a estas HORAS
}
