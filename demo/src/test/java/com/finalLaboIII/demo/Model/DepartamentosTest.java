package com.finalLaboIII.demo.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartamentosTest {

    @Test
    void testConstructorAndGetters() {
        Departamentos dpto = new Departamentos("Ingeniería");

        assertEquals("Ingeniería", dpto.getNombre());
    }

    @Test
    void testSetters() {
        Departamentos dpto = new Departamentos();
        dpto.setNombre("Ciencias Económicas");

        assertEquals("Ciencias Económicas", dpto.getNombre());
    }
}

