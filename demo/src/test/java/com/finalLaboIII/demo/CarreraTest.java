package com.finalLaboIII.demo;
import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Model.Profesor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarreraTest {
    private static Carrera carrera;
    private static Carrera c1;
    private static Carrera c2;
    private static Carrera c3;
    private static Carrera c4;
    private static Carrera c5;
    private static Materia materia;
    private static Profesor profesor;

    @BeforeAll
    public static void setUp() {
        Profesor profesor1 = new Profesor("Luciano", "Salotto", "Lic.");
        Materia materia1 = new Materia("Laboratorio 3", 1, profesor1);

    }
    @Test
    public void testNewCarrera(){
        Carrera carrera1 = new Carrera("Programacion", 4, 1);
        assertEquals("Programacion", carrera1.getNombre());
        assertEquals(4, carrera1.getCantidadCuatrimestre());
        assertEquals(1, carrera1.getIdDepartamento());
    }



}
