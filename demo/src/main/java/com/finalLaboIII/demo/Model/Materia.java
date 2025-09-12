package com.finalLaboIII.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
    private String nombreMateria;
    private int cantCuatrimestre;
    private Profesor profesor;
    private int id;

}
