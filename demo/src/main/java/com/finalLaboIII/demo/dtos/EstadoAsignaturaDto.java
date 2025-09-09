package com.finalLaboIII.demo.dtos;

import com.finalLaboIII.demo.Model.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoAsignaturaDto {
    private Asignatura.EstadoAsignatura estado;
    private Integer nota;
}
