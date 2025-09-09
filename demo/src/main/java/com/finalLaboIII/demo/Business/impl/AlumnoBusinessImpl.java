package com.finalLaboIII.demo.Business.impl;

import com.finalLaboIII.demo.Business.interfaces.AlumnoBusiness;
import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;
import com.finalLaboIII.demo.Persistence.Impl.AlumnoDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class AlumnoBusinessImpl implements AlumnoBusiness {

    private final AlumnoDaoImpl alumnoPersistence = new AlumnoDaoImpl();

    @Override
    public int crearAlumno(Alumno alumno) {
        return alumnoPersistence.crearAlumno(alumno);
    }

    @Override
    public void eliminarAlumno(Integer idAlumno) {
        alumnoPersistence.eliminarAlumno(idAlumno);
    }

    @Override
    public void actualizarAlumno(int idAlumno, Alumno alumno) {
        alumnoPersistence.actualizarAlumno(idAlumno, alumno);
    }

    @Override
    public Alumno obtenerAlumno(Integer idAlumno) {
        // delega directamente a la DAO
        return alumnoPersistence.obtenerAlumno(idAlumno);
    }

    @Override
    public void inscribirAlumno(Integer idAlumno, Integer idCarrera) {
        alumnoPersistence.inscribirAlumno(idAlumno, idCarrera);
    }

    @Override
    public void agregarAsignatura(Integer idAlumno, Integer idMateria) {
        alumnoPersistence.agregarAsignatura(idAlumno, idMateria);
    }

    @Override
    public void modificarEstadoAsignatura(Integer idAlumno, Integer idAsignatura,
                                          Asignatura.EstadoAsignatura estado, Integer nota) {
        alumnoPersistence.modificarEstadoAsignatura(idAlumno, idAsignatura, estado, nota);
    }
}
