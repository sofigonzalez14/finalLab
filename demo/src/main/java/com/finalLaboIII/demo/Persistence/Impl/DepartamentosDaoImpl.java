package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Departamentos;
import com.finalLaboIII.demo.Persistence.exceptions.DepartamentoNoEncontradoException;
import com.finalLaboIII.demo.Persistence.interfaces.DepartamentosDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class DepartamentosDaoImpl implements DepartamentosDao {

    public static Map<Integer, Departamentos> listaDepartamentos = new HashMap<>();
    private static int idContador = 1; // arrancamos en 1

    private int getIdContador() {
        return idContador++;
    }

    @Override
    public int crearDepartamento(Departamentos departamentos) {
        int id = getIdContador();
        Departamentos d = new Departamentos();
        d.setNombre(departamentos.getNombre());
        listaDepartamentos.put(id, d);
        return id;
    }

    @Override
    public void eliminarDepartamento(Integer idDepartamento) {
        if (!listaDepartamentos.containsKey(idDepartamento)) {
            throw new DepartamentoNoEncontradoException("Departamento no encontrado con id " + idDepartamento);
        }
        listaDepartamentos.remove(idDepartamento);
    }

    @Override
    public Departamentos buscarDepartamentobyNombre(String nombreDpto) {
        for (Departamentos departamentos : listaDepartamentos.values()) {
            if (departamentos.getNombre().equalsIgnoreCase(nombreDpto)) {
                return departamentos;
            }
        }
        throw new DepartamentoNoEncontradoException("No existe departamento con nombre " + nombreDpto);
    }

    @Override
    public Departamentos buscarDepartamentobyId(Integer idDepartamento) {
        Departamentos departamento = listaDepartamentos.get(idDepartamento);
        if (departamento == null) {
            throw new DepartamentoNoEncontradoException("Departamento no encontrado con id " + idDepartamento);
        }
        return departamento;
    }
}
