package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;
import com.finalLaboIII.demo.Persistence.exceptions.*;
import com.finalLaboIII.demo.Persistence.interfaces.AlumnoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AlumnoDaoImpl implements AlumnoDao {
    private static final Logger logger = LoggerFactory.getLogger(AlumnoDao.class);
    public static Map<Integer, Alumno> listaAlumno = new HashMap<>();

    public static int idContador = 0;
    public int getIdContador(){

        return idContador++;
    }
    public static int idContadorAsignatura = 0;
    public int getIdContadorAsignatura(){

        return idContadorAsignatura++;
    }
    @Override
    public int crearAlumno(Alumno alumno) {
        int id= getIdContador();
        Alumno a = new Alumno();
        a.setNombre(alumno.getNombre());
        a.setApellido(alumno.getApellido());
        a.setDni(alumno.getDni());
        listaAlumno.put(id, a);
        return id;
    }

    @Override
    public void eliminarAlumno(Integer idAlumno) {
        if(!listaAlumno.containsKey(idAlumno)){
            throw new RuntimeException();
        }
        listaAlumno.remove(idAlumno);
        logger.info("Lista carrera: " + listaAlumno.toString());
    }

    @Override
    public void actualizarAlumno(int idAlumno, Alumno alumno) {
        if(!listaAlumno.containsKey(idAlumno)){
            throw new AlumnoNoEncontradoException();
        }
        listaAlumno.replace(idAlumno, alumno);
    }

    @Override
    public void inscribirAlumno(Integer idAlumno, Integer idCarrera) {
        if(!listaAlumno.containsKey(idAlumno)){
            throw new AlumnoNoEncontradoException();
        }
        if(!CarreraDaoImpl.listaCarreras.containsKey(idCarrera)){
            throw new CarreraNoEncontradaException();
        }
        Alumno a = listaAlumno.get(idAlumno);
        if(a.getListaCarrerasInscriptas().size() >= 3){
            throw new LimiteDeCarrerasException();
        }
        a.getListaCarrerasInscriptas().add(idCarrera);

    }

    @Override
    public void agregarAsignatura(Integer idAlumno, Integer idMateria) {
        if(!listaAlumno.containsKey(idAlumno)){
            throw new AlumnoNoEncontradoException();
        }
        Alumno a = listaAlumno.get(idAlumno);
        if(!a.getListaAsignatura().containsKey(idMateria)){
            throw new MateriaNoEncontradaException();
        }
        Asignatura s = new Asignatura();
        s.setEstado(Asignatura.EstadoAsignatura.NO_CURSADA);
        a.getListaAsignatura().put(idMateria, s);
    }

    @Override
    public void modificarEstadoAsignatura(Integer idAlumno, Integer idMateria, Asignatura.EstadoAsignatura estado, Integer nota) {
        if(nota <= 0){
            throw new NotaInvalidaException();
        }
        if(nota <= 4 && estado == Asignatura.EstadoAsignatura.APROBADA){
            throw new EstadoInvalidoException();
        }
        if(!listaAlumno.containsKey(idAlumno)){
            throw new AlumnoNoEncontradoException();
        }
        Alumno a = listaAlumno.get(idAlumno);
        if(!a.getListaAsignatura().containsKey(idMateria)){
            throw new AsignaturaNoEncontradaException();
        }
        a.getListaAsignatura().get(idMateria).setEstado(estado);
        a.getListaAsignatura().get(idMateria).setNota(nota);

    }


}
