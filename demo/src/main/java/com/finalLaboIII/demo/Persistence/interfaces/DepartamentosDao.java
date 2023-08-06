package com.finalLaboIII.demo.Persistence.interfaces;

import com.finalLaboIII.demo.Model.Departamentos;

public interface DepartamentosDao {
    public int crearDepartamento (Departamentos departamentos);
    public void eliminarDepartamento (Integer idDepartamento);

    public Departamentos buscarDepartamentobyNombre (String nombreDpto);
}
