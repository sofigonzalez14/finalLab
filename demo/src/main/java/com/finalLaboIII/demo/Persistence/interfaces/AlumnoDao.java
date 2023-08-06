package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;

public interface AlumnoDao {
    public int crearAlumno (Alumno alumno);
    public void eliminarAlumno (Integer idAlumno);
    public void actualizarAlumno (int idAlumno, Alumno alumno);
    public void inscribirAlumno (Integer idAlumno, Integer idCarrera);
    public void agregarAsignatura (Integer idAlumno, Integer idMateria);
    public void modificarEstadoAsignatura (Integer idAlumno, Integer idMateria, Asignatura.EstadoAsignatura estado, Integer nota);

}
