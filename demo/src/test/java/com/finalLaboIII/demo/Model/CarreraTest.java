package com.finalLaboIII.demo.Model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CarreraTest {

    @Test
    void testConstructorAndGetters() {
        Carrera carrera = new Carrera("Ingeniería", 10, 1, 6);

        assertEquals("Ingeniería", carrera.getNombre());
        assertEquals(10, carrera.getCantidadMaterias());
        assertEquals(1, carrera.getIdDepartamento());
    }

    @Test
    void testSetters() {
        Carrera carrera = new Carrera();
        carrera.setNombre("Medicina");
        carrera.setCantidadMaterias(12);
        carrera.setIdDepartamento(2);
        carrera.setMateriaList(new HashMap<>());

        assertEquals("Medicina", carrera.getNombre());
        assertEquals(12, carrera.getCantidadMaterias());
        assertEquals(2, carrera.getIdDepartamento());
        assertNotNull(carrera.getMateriaList());
    }
}

