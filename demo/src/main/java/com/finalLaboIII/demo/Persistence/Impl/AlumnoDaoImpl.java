package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;
import com.finalLaboIII.demo.Persistence.exceptions.*;
import com.finalLaboIII.demo.Persistence.interfaces.AlumnoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class AlumnoDaoImpl implements AlumnoDao {
    private static final Logger logger = LoggerFactory.getLogger(AlumnoDao.class);

    public static Map<Integer, Alumno> listaAlumno = new HashMap<>();
    private static int idContador = 1;

    private int getIdContador() {
        return idContador++;
    }

    @Override
    public Alumno crearAlumno(Alumno alumno) {
        int id = getIdContador();
        alumno.setId(id);
        listaAlumno.put(id, alumno);
        return alumno;
    }

    public List<Alumno> listarAlumnos() {
        return new ArrayList<>(listaAlumno.values());
    }


    @Override
    public void eliminarAlumno(Integer idAlumno) {
        if (!listaAlumno.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException("Alumno no encontrado con ID " + idAlumno);
        }
        listaAlumno.remove(idAlumno);
        logger.info("Alumno eliminado. Lista actual: " + listaAlumno.toString());
    }

    @Override
    public void actualizarAlumno(int idAlumno, Alumno alumno) {
        if (!listaAlumno.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException("Alumno no encontrado con ID " + idAlumno);
        }
        listaAlumno.replace(idAlumno, alumno);
    }

    @Override
    public Alumno obtenerAlumno(Integer idAlumno) {
        Alumno alumno = listaAlumno.get(idAlumno);
        if (alumno == null) {
            throw new AlumnoNoEncontradoException("Alumno no encontrado con ID " + idAlumno);
        }
        return alumno;
    }

    @Override
    public void inscribirAlumno(Integer idAlumno, Integer idCarrera) {
        Alumno a = obtenerAlumno(idAlumno);

        if (!CarreraDaoImpl.listaCarreras.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException("Carrera no encontrada con ID " + idCarrera);
        }
        if (a.getListaCarrerasInscriptas().size() >= 3) {
            throw new LimiteDeCarrerasException("El alumno ya tiene 3 carreras inscritas");
        }
        a.getListaCarrerasInscriptas().add(idCarrera);
    }

    @Override
    public void agregarAsignatura(Integer idAlumno, Integer idMateria) {
        Alumno a = obtenerAlumno(idAlumno);

        if (a.getListaAsignatura().containsKey(idMateria)) {
            throw new AsignaturaYaExisteException("La asignatura ya existe en el alumno");
        }

        Asignatura asignatura = new Asignatura();
        asignatura.setEstado(Asignatura.EstadoAsignatura.NO_CURSADA);
        asignatura.setNota(null);

        a.getListaAsignatura().put(idMateria, asignatura);
    }

    @Override
    public void modificarEstadoAsignatura(Integer idAlumno, Integer idMateria,
                                          Asignatura.EstadoAsignatura estado, Integer nota) {
        Alumno a = obtenerAlumno(idAlumno);

        if (!a.getListaAsignatura().containsKey(idMateria)) {
            throw new AsignaturaNoEncontradaException("La asignatura no existe en el alumno");
        }
        if (nota <= 0) {
            throw new NotaInvalidaException("La nota debe ser mayor a 0");
        }
        if (nota < 4 && estado == Asignatura.EstadoAsignatura.APROBADA) {
            throw new EstadoInvalidoException("No puede aprobar con nota menor a 4");
        }

        Asignatura asignatura = a.getListaAsignatura().get(idMateria);
        asignatura.setEstado(estado);
        asignatura.setNota(nota);
    }
}

