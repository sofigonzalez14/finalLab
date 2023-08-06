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
    private String nombre;
    private String apellido;
    private int dni;
    private Set<Integer> listaCarrerasInscriptas = new HashSet<>();
    private Map<Integer, Asignatura> listaAsignatura = new HashMap<>();

    public Alumno(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
}
