package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.interfaces.MateriaDao;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import com.finalLaboIII.demo.Persistence.exceptions.NohayMateriasException;

import java.util.HashMap;
import java.util.Map;

public class MateriaDaoImpl implements MateriaDao {
    public static Map<Integer, Materia> listaMaterias = new HashMap<>();
    public static int idContador = 0;
    public int getIdContador(){

        return idContador++;
    }
    @Override
    public int crearMateria(Materia materia) {
        int id = getIdContador();
        Materia m = new Materia();
        m.setNombreMateria(materia.getNombreMateria());
        m.setProfesor(materia.getProfesor());
        m.setCantCuatrimestre(materia.getCantCuatrimestre());
        listaMaterias.put(id, m);
        return id;
    }

    @Override
    public void actualizarMateria(int idMateria, Materia materia) {
        if(!listaMaterias.containsKey(idMateria)){
            throw new MateriaNoEncontradaException();
        }
        listaMaterias.replace(idMateria, materia);
    }

    @Override
    public void eliminarMateria(Integer idMateria) {
        if(!listaMaterias.containsKey(idMateria)){
            throw new MateriaNoEncontradaException();
        }
        listaMaterias.remove(idMateria);

    }

    @Override
    public Materia obtenerMateria(String nombreMateria) {
        for (Materia materia : listaMaterias.values()) {
            if (materia.getNombreMateria().equals(nombreMateria)) {
                return materia;
            }
        }
        throw new NohayMateriasException();
    }

}
