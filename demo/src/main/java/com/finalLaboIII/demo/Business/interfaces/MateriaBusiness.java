package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Materia;

public interface MateriaBusiness {
    int crearMateria(Materia materia);
    void actualizarMateria(int idMateria, Materia materia);
    void eliminarMateria(Integer idMateria);
    Materia obtenerMateria(String nombreMateria);   // buscar por nombre
    Materia obtenerMateriaPorId(Integer idMateria); // buscar por ID (nuevo)
}
