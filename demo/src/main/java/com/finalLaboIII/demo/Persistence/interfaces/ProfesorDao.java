package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Profesor;

public interface ProfesorDao {
    int crearProfesor(Profesor profesor);
    void eliminarProfesor(Integer idProfesor);
    Profesor obtenerProfesor(String nombreProfesor);
    Profesor obtenerProfesorPorId(Integer idProfesor);
}
