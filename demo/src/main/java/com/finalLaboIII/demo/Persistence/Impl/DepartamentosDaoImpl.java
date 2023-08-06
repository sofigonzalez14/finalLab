package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Departamentos;
import com.finalLaboIII.demo.Persistence.interfaces.DepartamentosDao;
import com.finalLaboIII.demo.Persistence.exceptions.DepartamentoNoEncontradoException;

import java.util.HashMap;
import java.util.Map;

public class DepartamentosDaoImpl implements DepartamentosDao {
    public Map<Integer, Departamentos> listaDepartamentos = new HashMap<>();
    public static int idContador = 0;
    public int getIdContador(){

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
        if(!listaDepartamentos.containsKey(idDepartamento)){
            throw new DepartamentoNoEncontradoException();
        }
        listaDepartamentos.remove(idDepartamento);
    }

    @Override
    public Departamentos buscarDepartamentobyNombre(String nombreDpto) {
        for (Departamentos departamentos : listaDepartamentos.values()) {
            if (departamentos.getNombre().equals(nombreDpto)) {
                return departamentos;
            }
        }
        throw new DepartamentoNoEncontradoException();

    }


}
