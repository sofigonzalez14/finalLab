package com.finalLaboIII.demo.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MateriaTest {

    @Test
    void testConstructorAndGetters() {
        Profesor profesor = new Profesor("Ana", "Martinez", "Lic.");
        Materia materia = new Materia("Programación", 2, profesor);

        assertEquals("Programación", materia.getNombreMateria());
        assertEquals(2, materia.getCantCuatrimestre());
        assertEquals(profesor, materia.getProfesor());
    }

    @Test
    void testSetters() {
        Profesor profesor = new Profesor("Juan", "Lopez", "Dr.");
        Materia materia = new Materia();
        materia.setNombreMateria("Matemática");
        materia.setCantCuatrimestre(3);
        materia.setProfesor(profesor);

        assertEquals("Matemática", materia.getNombreMateria());
        assertEquals(3, materia.getCantCuatrimestre());
        assertEquals("Juan", materia.getProfesor().getNombre());
    }
}

