package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.exceptions.ProfesorNoEncontradoException;
import com.finalLaboIII.demo.Persistence.interfaces.ProfesorDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class ProfesorDaoImpl implements ProfesorDao {

    public static Map<Integer, Profesor> listaProfesores = new HashMap<>();
    private static int idContador = 1; // arrancamos en 1

    private int getIdContador() {
        return idContador++;
    }

    @Override
    public int crearProfesor(Profesor profesor) {
        int id = getIdContador();
        profesor.setId(id);
        listaProfesores.put(id, profesor);
        return id;
    }


    @Override
    public void eliminarProfesor(Integer idProfesor) {
        if (!listaProfesores.containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException("Profesor no encontrado con id " + idProfesor);
        }
        listaProfesores.remove(idProfesor);
    }

    @Override
    public Profesor obtenerProfesor(String nombreProfesor) {
        for (Profesor profesor : listaProfesores.values()) {
            if (profesor.getNombre().equalsIgnoreCase(nombreProfesor)) {
                return profesor;
            }
        }
        throw new ProfesorNoEncontradoException("No existe profesor con nombre " + nombreProfesor);
    }

    @Override
    public Profesor obtenerProfesorPorId(Integer idProfesor) {
        Profesor profesor = listaProfesores.get(idProfesor);
        if (profesor == null) {
            throw new ProfesorNoEncontradoException("Profesor no encontrado con id " + idProfesor);
        }
        return profesor;
    }
}
