package com.finalLaboIII.demo.Business.impl;

import com.finalLaboIII.demo.Business.interfaces.MateriaBusiness;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.Impl.MateriaDaoImpl;


public class MateriaBusinessImpl implements MateriaBusiness {
    MateriaDaoImpl materiaPersistence = new MateriaDaoImpl();
    @Override
    public int crearMateria(Materia materia) {
        return materiaPersistence.crearMateria(materia);
    }

    @Override
    public void actualizarMateria(int idMateria, Materia materia) {
        materiaPersistence.actualizarMateria(idMateria, materia);
    }


    @Override
    public void eliminarMateria(Integer idMateria) {
        materiaPersistence.eliminarMateria(idMateria);
    }

    @Override
    public Materia obtenerMateria(String nombreMateria) {
        return materiaPersistence.obtenerMateria(nombreMateria);
    }


}
