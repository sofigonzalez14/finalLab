package com.finalLaboIII.demo.Business.impl;

import com.finalLaboIII.demo.Business.interfaces.MateriaBusiness;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.Impl.MateriaDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class MateriaBusinessImpl implements MateriaBusiness {

    private final MateriaDaoImpl materiaPersistence = new MateriaDaoImpl();

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

    @Override
    public Materia obtenerMateriaPorId(Integer idMateria) {
        return materiaPersistence.obtenerMateriaPorId(idMateria);
    }
}

