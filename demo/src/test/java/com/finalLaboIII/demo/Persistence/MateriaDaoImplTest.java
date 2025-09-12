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
        profesor = new Profesor("Laura", "Perez", "Ing.",1);
    }

    @Test
    void testCrearMateria() {
        Materia materia = new Materia("Algoritmos", 1, profesor,1);
        int id = materiaDao.crearMateria(materia);

        assertTrue(MateriaDaoImpl.listaMaterias.containsKey(id));
        assertEquals("Algoritmos", MateriaDaoImpl.listaMaterias.get(id).getNombreMateria());
    }

    @Test
    void testObtenerMateriaPorId() {
        Materia materia = new Materia("Bases de Datos", 2, profesor,2);
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
        materiaDao.crearMateria(new Materia("Redes", 3, profesor,3));
        Materia result = materiaDao.obtenerMateria("Redes");

        assertEquals("Redes", result.getNombreMateria());
    }

    @Test
    void testActualizarMateria() {
        int id = materiaDao.crearMateria(new Materia("Sistemas", 4, profesor,1));
        Materia actualizada = new Materia("Sistemas Operativos", 4, profesor,2);

        materiaDao.actualizarMateria(id, actualizada);
        assertEquals("Sistemas Operativos", MateriaDaoImpl.listaMaterias.get(id).getNombreMateria());
    }

    @Test
    void testEliminarMateria() {
        int id = materiaDao.crearMateria(new Materia("Matemática", 2, profesor,3));
        materiaDao.eliminarMateria(id);

        assertFalse(MateriaDaoImpl.listaMaterias.containsKey(id));
    }
}
