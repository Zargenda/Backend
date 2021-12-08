package com.backend.Backend.model;
import javax.persistence.*;

import lombok.*;
@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Aula {
    private @Id Long  id;
    private String acronimo;
    private String nombre;
    private int capacidad;
    private int edificio;
    private String observaciones;

    public Aula(Long id, String acronimo, String nombre, int capacidad, int edificio, String observaciones) {
        this.id = id;
        this.acronimo = acronimo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.edificio = edificio;
        this.observaciones = observaciones;
    }
}
