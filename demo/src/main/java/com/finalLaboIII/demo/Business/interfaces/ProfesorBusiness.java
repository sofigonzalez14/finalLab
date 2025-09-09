package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Profesor;

public interface ProfesorBusiness {
    int crearProfesor(Profesor profesor);
    void eliminarProfesor(Integer idProfesor);
    Profesor obtenerProfesor(String nombreProfesor);   // buscar por nombre
    Profesor obtenerProfesorPorId(Integer idProfesor); // buscar por ID (nuevo)
}
