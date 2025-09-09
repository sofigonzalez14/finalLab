package com.finalLaboIII.demo.Business.impl;

import com.finalLaboIII.demo.Business.interfaces.ProfesorBusiness;
import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.Impl.ProfesorDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class ProfesorBusinessImpl implements ProfesorBusiness {

    private final ProfesorDaoImpl profesorPersistence = new ProfesorDaoImpl();

    @Override
    public int crearProfesor(Profesor profesor) {
        return profesorPersistence.crearProfesor(profesor);
    }

    @Override
    public void eliminarProfesor(Integer idProfesor) {
        profesorPersistence.eliminarProfesor(idProfesor);
    }

    @Override
    public Profesor obtenerProfesor(String nombreProfesor) {
        return profesorPersistence.obtenerProfesor(nombreProfesor);
    }

    @Override
    public Profesor obtenerProfesorPorId(Integer idProfesor) {
        return profesorPersistence.obtenerProfesorPorId(idProfesor);
    }
}
