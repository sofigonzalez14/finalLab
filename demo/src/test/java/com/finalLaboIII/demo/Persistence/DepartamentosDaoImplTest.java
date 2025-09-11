package com.finalLaboIII.demo.Persistence;

import com.finalLaboIII.demo.Model.Departamentos;
import com.finalLaboIII.demo.Persistence.Impl.DepartamentosDaoImpl;
import com.finalLaboIII.demo.Persistence.exceptions.DepartamentoNoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentosDaoImplTest {

    private DepartamentosDaoImpl dao;

    @BeforeEach
    void setUp() {
        dao = new DepartamentosDaoImpl();
        DepartamentosDaoImpl.listaDepartamentos.clear();
    }

    @Test
    void testCrearDepartamento() {
        Departamentos dpto = new Departamentos("Humanidades");
        int id = dao.crearDepartamento(dpto);

        assertTrue(DepartamentosDaoImpl.listaDepartamentos.containsKey(id));
        assertEquals("Humanidades", DepartamentosDaoImpl.listaDepartamentos.get(id).getNombre());
    }

    @Test
    void testEliminarDepartamento() {
        int id = dao.crearDepartamento(new Departamentos("Exactas"));
        dao.eliminarDepartamento(id);

        assertFalse(DepartamentosDaoImpl.listaDepartamentos.containsKey(id));
    }

    @Test
    void testEliminarDepartamentoNotFound() {
        assertThrows(DepartamentoNoEncontradoException.class, () -> dao.eliminarDepartamento(99));
    }

    @Test
    void testBuscarDepartamentoPorNombre() {
        dao.crearDepartamento(new Departamentos("Ingeniería"));

        Departamentos result = dao.buscarDepartamentobyNombre("Ingeniería");

        assertEquals("Ingeniería", result.getNombre());
    }

    @Test
    void testBuscarDepartamentoPorNombreNotFound() {
        assertThrows(DepartamentoNoEncontradoException.class,
                () -> dao.buscarDepartamentobyNombre("NoExiste"));
    }

    @Test
    void testBuscarDepartamentoPorId() {
        int id = dao.crearDepartamento(new Departamentos("Arquitectura"));
        Departamentos result = dao.buscarDepartamentobyId(id);

        assertEquals("Arquitectura", result.getNombre());
    }

    @Test
    void testBuscarDepartamentoPorIdNotFound() {
        assertThrows(DepartamentoNoEncontradoException.class,
                () -> dao.buscarDepartamentobyId(50));
    }
}
