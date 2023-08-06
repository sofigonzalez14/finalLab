package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Materia;

public interface MateriaBusiness {
    public int crearMateria (Materia materia);
    public void actualizarMateria(int idMateria, Materia materia);
    public void eliminarMateria (Integer idMateria);
    public Materia obtenerMateria (String nombreMateria);

}
