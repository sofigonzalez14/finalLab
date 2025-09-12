package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Departamentos;

public interface DepartamentosDao {
    int crearDepartamento(Departamentos departamentos);
    void eliminarDepartamento(Integer idDepartamento);
    Departamentos buscarDepartamentobyNombre(String nombreDpto);
    Departamentos buscarDepartamentobyId(Integer idDepartamento);
}
