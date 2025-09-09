package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Profesor;

public interface ProfesorDao {
    int crearProfesor(Profesor profesor);
    void eliminarProfesor(Integer idProfesor);
    Profesor obtenerProfesor(String nombreProfesor);   // buscar por nombre
    Profesor obtenerProfesorPorId(Integer idProfesor); // nuevo: buscar por ID
}
