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

    private String aula;
    private Long idReserva1;
    private Long idReserva2;
    private String descripcion;//Exite conflicto con el AULA  el DIA por AsignaturaA[horaini,horafin] y por AsignaturaB[horaini,horafin]
}
