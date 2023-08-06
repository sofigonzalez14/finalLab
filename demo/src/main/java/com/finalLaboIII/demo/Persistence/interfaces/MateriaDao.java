package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Materia;

public interface MateriaDao {
    public int crearMateria (Materia materia);
    public void actualizarMateria(int idMateria, Materia materia);
    public void eliminarMateria (Integer idMateria);
    public Materia obtenerMateria (String nombreMateria);

}
