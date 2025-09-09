package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Model.Materia;

import java.util.List;

public interface CarreraDao {
    void eliminarCarrera(Integer idCarrera);
    int crearCarrera(Carrera carrera);
    List<Materia> obtenerMateria_Carrera(Integer idCarrera);   // ahora devuelve lista
    List<Carrera> obtenerCarrera_Departamento(Integer idDepartamento); // ahora devuelve lista
    void actualizarCarrera(int idCarrera, Carrera carrera);
}
