package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Materia;

public interface MateriaDao {
    int crearMateria(Materia materia);
    void actualizarMateria(int idMateria, Materia materia);
    void eliminarMateria(Integer idMateria);
    Materia obtenerMateria(String nombreMateria);
    Materia obtenerMateriaPorId(Integer idMateria);
}
