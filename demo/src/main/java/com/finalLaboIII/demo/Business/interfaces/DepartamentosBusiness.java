package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Departamentos;

public interface DepartamentosBusiness {
    int crearDepartamento(Departamentos departamentos);
    void eliminarDepartamento(Integer idDepartamento);
    Departamentos buscarDepartamentobyNombre(String nombreDpto);
    Departamentos buscarDepartamentobyId(Integer idDepartamento); // nuevo
}
