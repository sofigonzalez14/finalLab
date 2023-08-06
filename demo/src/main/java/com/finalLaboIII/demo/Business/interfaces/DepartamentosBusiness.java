package com.finalLaboIII.demo.Business.interfaces;

import com.finalLaboIII.demo.Model.Departamentos;

public interface DepartamentosBusiness {
    public int crearDepartamento (Departamentos departamentos);
    public void eliminarDepartamento (Integer idDepartamento);
    public Departamentos buscarDepartamentobyNombre (String nombreDpto);
}
