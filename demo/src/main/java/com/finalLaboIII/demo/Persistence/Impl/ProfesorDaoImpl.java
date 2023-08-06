package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.interfaces.ProfesorDao;
import com.finalLaboIII.demo.Persistence.exceptions.NoExisteProfesorException;
import com.finalLaboIII.demo.Persistence.exceptions.ProfesorNoEncontradoException;

import java.util.HashMap;
import java.util.Map;

public class ProfesorDaoImpl implements ProfesorDao {
    public static Map<Integer, Profesor> listaProfesores = new HashMap<>();
    public static int idContador = 0;
    public int getIdContador(){

        return idContador++;
    }
    @Override
    public int crearProfesor(Profesor profesor) {
        int id = getIdContador();
        Profesor p = new Profesor();
        p.setNombre(profesor.getNombre());
        p.setTitulo(profesor.getTitulo());
        p.setApellido(profesor.getApellido());
        listaProfesores.put(id, p);
        return id;
    }

    @Override
    public void eliminarProfesor(Integer idProfesor) {
        if(!listaProfesores.containsKey(idProfesor)){
            throw new ProfesorNoEncontradoException();
        }
        listaProfesores.remove(idProfesor);
    }

    @Override
    public Profesor obtenerProfesor(String nombreProfesor) {
        for (Profesor profesor : listaProfesores.values()){
            if(profesor.getNombre().equals(nombreProfesor)){
                return profesor;
            }
        }
        throw new NoExisteProfesorException();
    }


}
