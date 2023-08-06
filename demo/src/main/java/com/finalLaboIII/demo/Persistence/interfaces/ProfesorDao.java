package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Profesor;

public interface ProfesorDao {
    public int crearProfesor(Profesor profesor);
    public void eliminarProfesor (Integer idProfesor);
    public Profesor obtenerProfesor (String nombreProfesor);
}
