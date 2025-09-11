package com.finalLaboIII.demo.Persistence;

import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.Impl.MateriaDaoImpl;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MateriaDaoImplTest {

    private MateriaDaoImpl materiaDao;
    private Profesor profesor;

    @BeforeEach
    void setUp() {
        materiaDao = new MateriaDaoImpl();
        MateriaDaoImpl.listaMaterias.clear();
        profesor = new Profesor("Laura", "Perez", "Ing.");
    }

    @Test
    void testCrearMateria() {
        Materia materia = new Materia("Algoritmos", 1, profesor);
        int id = materiaDao.crearMateria(materia);

        assertTrue(MateriaDaoImpl.listaMaterias.containsKey(id));
        assertEquals("Algoritmos", MateriaDaoImpl.listaMaterias.get(id).getNombreMateria());
    }

    @Test
    void testObtenerMateriaPorId() {
        Materia materia = new Materia("Bases de Datos", 2, profesor);
        int id = materiaDao.crearMateria(materia);

        Materia result = materiaDao.obtenerMateriaPorId(id);
        assertEquals("Bases de Datos", result.getNombreMateria());
    }

    @Test
    void testObtenerMateriaPorIdNotFound() {
        assertThrows(MateriaNoEncontradaException.class, () -> materiaDao.obtenerMateriaPorId(99));
    }

    @Test
    void testObtenerMateriaPorNombre() {
        materiaDao.crearMateria(new Materia("Redes", 3, profesor));
        Materia result = materiaDao.obtenerMateria("Redes");

        assertEquals("Redes", result.getNombreMateria());
    }

    @Test
    void testActualizarMateria() {
        int id = materiaDao.crearMateria(new Materia("Sistemas", 4, profesor));
        Materia actualizada = new Materia("Sistemas Operativos", 4, profesor);

        materiaDao.actualizarMateria(id, actualizada);
        assertEquals("Sistemas Operativos", MateriaDaoImpl.listaMaterias.get(id).getNombreMateria());
    }

    @Test
    void testEliminarMateria() {
        int id = materiaDao.crearMateria(new Materia("Matemática", 2, profesor));
        materiaDao.eliminarMateria(id);

        assertFalse(MateriaDaoImpl.listaMaterias.containsKey(id));
    }
}
