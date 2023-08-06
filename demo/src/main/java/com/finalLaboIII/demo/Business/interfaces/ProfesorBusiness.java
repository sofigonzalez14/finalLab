package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Profesor;

public interface ProfesorBusiness {
    public int crearProfesor(Profesor profesor);
    public void eliminarProfesor (Integer idProfesor);
    public Profesor obtenerProfesor (String nombreProfesor);
}
