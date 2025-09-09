package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;

public interface AlumnoBusiness {
    int crearAlumno(Alumno alumno);
    Alumno obtenerAlumno(Integer idAlumno);
    void eliminarAlumno(Integer idAlumno);
    void actualizarAlumno(int idAlumno, Alumno alumno);
    void inscribirAlumno(Integer idAlumno, Integer idCarrera);
    void agregarAsignatura(Integer idAlumno, Integer idMateria);
    void modificarEstadoAsignatura(Integer idAlumno, Integer idMateria,
                                   Asignatura.EstadoAsignatura estado, Integer nota);

}
