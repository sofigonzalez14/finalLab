package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Model.Materia;

import java.util.List;

public interface CarreraDao {
    void eliminarCarrera(Integer idCarrera);
    int crearCarrera(Carrera carrera);
    Carrera obtenerCarrera(Integer idCarrera);
    List<Carrera> listarCarreras();
    List<Materia> obtenerMateria_Carrera(Integer idCarrera);
    List<Carrera> obtenerCarrera_Departamento(Integer idDepartamento);
    void actualizarCarrera(int idCarrera, Carrera carrera);
}
