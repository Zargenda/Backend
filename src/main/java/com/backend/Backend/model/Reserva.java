package com.backend.Backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idHorario;
    private String nombre_Asignatura;
    private String fecha;
    private Date horainicio;
    private Date horafin;
    private String aulaId;
    private String frequency;

    public Reserva(long idHorario, String nombre_Asignatura, String fecha, Date horainicio, Date horafin, String aulaId, String frequency) {
        this.idHorario = idHorario;
        this.nombre_Asignatura = nombre_Asignatura;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.aulaId = aulaId;
        this.frequency = frequency;
    }
}
