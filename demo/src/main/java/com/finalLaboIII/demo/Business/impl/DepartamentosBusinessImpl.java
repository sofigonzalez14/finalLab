package com.finalLaboIII.demo.Business.impl;

import com.finalLaboIII.demo.Business.interfaces.DepartamentosBusiness;
import com.finalLaboIII.demo.Model.Departamentos;
import com.finalLaboIII.demo.Persistence.Impl.DepartamentosDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class DepartamentosBusinessImpl implements DepartamentosBusiness {

    private final DepartamentosDaoImpl departamentoPersistence = new DepartamentosDaoImpl();

    @Override
    public int crearDepartamento(Departamentos departamentos) {
        return departamentoPersistence.crearDepartamento(departamentos);
    }

    @Override
    public void eliminarDepartamento(Integer idDepartamento) {
        departamentoPersistence.eliminarDepartamento(idDepartamento);
    }

    @Override
    public Departamentos buscarDepartamentobyNombre(String nombreDpto) {
        return departamentoPersistence.buscarDepartamentobyNombre(nombreDpto);
    }

    @Override
    public Departamentos buscarDepartamentobyId(Integer idDepartamento) {
        return departamentoPersistence.buscarDepartamentobyId(idDepartamento);
    }
}
