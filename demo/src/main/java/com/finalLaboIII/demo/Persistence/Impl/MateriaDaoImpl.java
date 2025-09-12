package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import com.finalLaboIII.demo.Persistence.interfaces.MateriaDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class MateriaDaoImpl implements MateriaDao {

    public static Map<Integer, Materia> listaMaterias = new HashMap<>();
    private static int idContador = 1; // arrancamos en 1

    private int getIdContador() {
        return idContador++;
    }


    @Override
    public int crearMateria(Materia materia) {
        int id = getIdContador();
        materia.setId(id);
        listaMaterias.put(id, materia);
        return id;
    }


    @Override
    public void actualizarMateria(int idMateria, Materia materia) {
        if (!listaMaterias.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException("Materia no encontrada con id " + idMateria);
        }
        listaMaterias.put(idMateria, materia);
    }

    @Override
    public void eliminarMateria(Integer idMateria) {
        if (!listaMaterias.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException("Materia no encontrada con id " + idMateria);
        }
        listaMaterias.remove(idMateria);
    }

    @Override
    public Materia obtenerMateria(String nombreMateria) {
        for (Materia materia : listaMaterias.values()) {
            if (materia.getNombreMateria().equalsIgnoreCase(nombreMateria)) {
                return materia;
            }
        }
        throw new MateriaNoEncontradaException("No se encontró materia con nombre " + nombreMateria);
    }


    @Override
    public Materia obtenerMateriaPorId(Integer idMateria) {
        Materia materia = listaMaterias.get(idMateria);
        if (materia == null) {
            throw new MateriaNoEncontradaException("Materia no encontrada con id " + idMateria);
        }
        return materia;
    }
}
