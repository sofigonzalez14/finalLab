package com.finalLaboIII.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {

    private Integer idAsignatura;
    private EstadoAsignatura estado;
    private Integer nota;

    public enum EstadoAsignatura {
        NO_CURSADA,
        CURSADA,
        APROBADA
    }
}
