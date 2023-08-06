package com.finalLaboIII.demo.Model;
import com.finalLaboIII.demo.Model.Materia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AlumnoTest {
    private static Alumno alumno;
    private static Materia m1;
    private static Materia m2;
    private static Materia m3;
    private static Materia m4;
    private static Profesor profesor;



    @BeforeAll
    public static void setUp() {
        Profesor profesor1 = new Profesor("Luciano", "Salotto", "Lic.");
        m1 = new Materia("Laboratorio 3", 1, profesor1);
        m2 = new Materia("Laboratorio 1", 1, profesor1);


    }
    @Test
    public void testNewAlumno() {
        Alumno alumno1 = new Alumno("sofia", "gonzalez", 44518848);
        assertEquals("sofia", alumno1.getNombre());
        assertEquals("gonzalez", alumno1.getApellido());
        assertEquals(44518848, alumno1.getDni());
    }

}