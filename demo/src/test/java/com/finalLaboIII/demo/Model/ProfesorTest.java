package com.finalLaboIII.demo.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfesorTest {

    @Test
    void testCrearProfesorConConstructor() {
        Profesor profesor = new Profesor("María", "López", "Ingeniera",1);

        assertEquals("María", profesor.getNombre());
        assertEquals("López", profesor.getApellido());
        assertEquals("Ingeniera", profesor.getTitulo());
    }

    @Test
    void testSettersAndGetters() {
        Profesor profesor = new Profesor();
        profesor.setNombre("Carlos");
        profesor.setApellido("Pérez");
        profesor.setTitulo("Doctor");

        assertEquals("Carlos", profesor.getNombre());
        assertEquals("Pérez", profesor.getApellido());
        assertEquals("Doctor", profesor.getTitulo());
    }
}

