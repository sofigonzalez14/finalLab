package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Carrera;

public interface CarreraDao {
    public  void eliminarCarrera(Integer idCarrera) ;
    public int crearCarrera(Carrera carrera);
    public void obtenerMateria_Carrera(Integer idCarrera);
    public void obtenerCarrera_Departamento(Integer idDepartamento);
    public void actualizarCarrera (int idCarrera, Carrera carrera);
}
