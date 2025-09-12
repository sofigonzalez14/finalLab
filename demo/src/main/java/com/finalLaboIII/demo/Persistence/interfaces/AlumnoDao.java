package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;

public interface AlumnoDao {
    Alumno crearAlumno(Alumno alumno);

    void eliminarAlumno(Integer idAlumno);
    void actualizarAlumno(int idAlumno, Alumno alumno);
    Alumno obtenerAlumno(Integer idAlumno);
    void inscribirAlumno(Integer idAlumno, Integer idCarrera);
    void agregarAsignatura(Integer idAlumno, Integer idMateria);
    void modificarEstadoAsignatura(Integer idAlumno, Integer idMateria,
                                   Asignatura.EstadoAsignatura estado, Integer nota);
}
