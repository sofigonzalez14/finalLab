package com.finalLaboIII.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    private Integer id;

    private String nombre;
    private String apellido;
    private int dni;

    // carreras inscriptas por id
    private Set<Integer> listaCarrerasInscriptas = new HashSet<>();

    // materias inscriptas (idMateria -> Asignatura)
    private Map<Integer, Asignatura> listaAsignatura = new HashMap<>();

    // constructor específico que se usa en DAO (sin id)
    public Alumno(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
}

