package com.finalLaboIII.demo.Business;

import com.finalLaboIII.demo.Business.impl.DepartamentosBusinessImpl;
import com.finalLaboIII.demo.Model.Departamentos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartamentosBusinessImplTest {

    private DepartamentosBusinessImpl departamentosBusiness;

    @BeforeEach
    void setUp() {
        departamentosBusiness = new DepartamentosBusinessImpl();
    }

    @Test
    void testCrearDepartamento() {
        Departamentos dpto = new Departamentos("Ciencias Exactas");
        int id = departamentosBusiness.crearDepartamento(dpto);

        assertTrue(id > 0);
        assertNotNull(departamentosBusiness.buscarDepartamentobyId(id));
    }
}
