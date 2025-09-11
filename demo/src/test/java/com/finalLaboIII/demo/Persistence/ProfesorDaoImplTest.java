package com.finalLaboIII.demo.Persistence;

import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.Impl.ProfesorDaoImpl;
import com.finalLaboIII.demo.Persistence.exceptions.ProfesorNoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorDaoImplTest {

    private ProfesorDaoImpl profesorDao;

    @BeforeEach
    void setUp() {
        profesorDao = new ProfesorDaoImpl();
        ProfesorDaoImpl.listaProfesores.clear(); // limpiar mapa antes de cada test
    }

    @Test
    void testCrearProfesor() {
        Profesor profesor = new Profesor("Laura", "Martínez", "Licenciada");
        int id = profesorDao.crearProfesor(profesor);

        assertNotNull(ProfesorDaoImpl.listaProfesores.get(id));
        assertEquals("Laura", ProfesorDaoImpl.listaProfesores.get(id).getNombre());
    }

    @Test
    void testEliminarProfesorExistente() {
        Profesor profesor = new Profesor("Juan", "Gómez", "Doctor");
        int id = profesorDao.crearProfesor(profesor);

        profesorDao.eliminarProfesor(id);

        assertFalse(ProfesorDaoImpl.listaProfesores.containsKey(id));
    }

    @Test
    void testEliminarProfesorInexistente() {
        assertThrows(ProfesorNoEncontradoException.class, () -> profesorDao.eliminarProfesor(999));
    }

    @Test
    void testObtenerProfesorPorNombre() {
        Profesor profesor = new Profesor("Ana", "Sosa", "Ingeniera");
        profesorDao.crearProfesor(profesor);

        Profesor encontrado = profesorDao.obtenerProfesor("Ana");
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void testObtenerProfesorPorId() {
        Profesor profesor = new Profesor("Pedro", "Fernández", "Arquitecto");
        int id = profesorDao.crearProfesor(profesor);

        Profesor encontrado = profesorDao.obtenerProfesorPorId(id);
        assertEquals("Pedro", encontrado.getNombre());
    }

    @Test
    void testObtenerProfesorPorIdInexistente() {
        assertThrows(ProfesorNoEncontradoException.class, () -> profesorDao.obtenerProfesorPorId(500));
    }
}

