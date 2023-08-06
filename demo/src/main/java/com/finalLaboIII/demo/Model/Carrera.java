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
    private String nombre;
    private int cantidadCuatrimestre;
    private int idDepartamento;
    private Map<Integer, Materia> materiaList;

    public Carrera(String nombre, int cantidadCuatrimestre, int idDepartamento) {
        this.nombre = nombre;
        this.cantidadCuatrimestre = cantidadCuatrimestre;
        this.idDepartamento = idDepartamento;
    }
}
