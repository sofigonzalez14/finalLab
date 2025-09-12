package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Model.Materia;

import java.util.List;

public interface CarreraBusiness {
    void eliminarCarrera(Integer idCarrera);
    int crearCarrera(Carrera carrera);
    Carrera obtenerCarrera(Integer idCarrera);
    List<Carrera> listarTodas();
    List<Materia> obtenerMateria_Carrera(Integer idCarrera);
    List<Carrera> obtenerCarrera_Departamento(Integer idDepartamento);
    void actualizarCarrera(int idCarrera, Carrera carrera);
}


