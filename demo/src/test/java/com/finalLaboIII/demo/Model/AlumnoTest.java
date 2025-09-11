package com.finalLaboIII.demo.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlumnoTest {

    @Test
    public void testConstructorConParametros() {
        Alumno alumno = new Alumno("Martina", "Lopez", 50123456);

        assertEquals("Martina", alumno.getNombre());
        assertEquals("Lopez", alumno.getApellido());
        assertEquals(50123456, alumno.getDni());
    }

    @Test
    public void testSettersAndGetters() {
        Alumno alumno = new Alumno();
        alumno.setNombre("Valentin");
        alumno.setApellido("Perez");
        alumno.setDni(48999111);

        assertEquals("Valentin", alumno.getNombre());
        assertEquals("Perez", alumno.getApellido());
        assertEquals(48999111, alumno.getDni());
    }

    @Test
    public void testColeccionesInicializadas() {
        Alumno alumno = new Alumno();

        // Al crearse debe tener las colecciones vacías pero no nulas
        assertNotNull(alumno.getListaCarrerasInscriptas());
        assertNotNull(alumno.getListaAsignatura());

        // Probar que puedo agregar datos
        alumno.getListaCarrerasInscriptas().add(10);
        alumno.getListaAsignatura().put(101, new Asignatura(101, Asignatura.EstadoAsignatura.APROBADA, 9));

        assertTrue(alumno.getListaCarrerasInscriptas().contains(10));
        assertEquals(1, alumno.getListaAsignatura().size());
        assertEquals(9, alumno.getListaAsignatura().get(101).getNota());
    }
}
