package com.finalLaboIII.demo.Business.dtos;

import com.finalLaboIII.demo.Model.Asignatura;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EstadoAsignaturaDto {
    private Asignatura.EstadoAsignatura estado;
    private Integer nota;

}
