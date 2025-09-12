package com.finalLaboIII.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrera {
    private Integer id;
    private String nombre;
    private int cantidadMaterias;
    private int idDepartamento;
    private int duracionAnios;

    private Map<Integer, Materia> materiaList;

    public Carrera(String nombre, int cantidadMaterias, int idDepartamento, int duracionAnios) {
        this.nombre = nombre;
        this.cantidadMaterias = cantidadMaterias;
        this.idDepartamento = idDepartamento;
        this.duracionAnios = duracionAnios;
    }
}

