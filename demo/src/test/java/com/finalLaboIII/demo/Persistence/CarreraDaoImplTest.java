package com.finalLaboIII.demo.Persistence;

import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl;
import com.finalLaboIII.demo.Persistence.exceptions.CarreraNoEncontradaException;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarreraDaoImplTest {

    private CarreraDaoImpl carreraDao;

    @BeforeEach
    void setUp() {
        carreraDao = new CarreraDaoImpl();
        CarreraDaoImpl.listaCarreras.clear();
    }

    @Test
    void testCrearCarrera() {
        Carrera carrera = new Carrera("Ingeniería", 10, 1);
        int id = carreraDao.crearCarrera(carrera);

        assertTrue(CarreraDaoImpl.listaCarreras.containsKey(id));
        assertEquals("Ingeniería", CarreraDaoImpl.listaCarreras.get(id).getNombre());
    }

    @Test
    void testEliminarCarrera() {
        int id = carreraDao.crearCarrera(new Carrera("Medicina", 12, 2));
        carreraDao.eliminarCarrera(id);

        assertFalse(CarreraDaoImpl.listaCarreras.containsKey(id));
    }

    @Test
    void testEliminarCarreraNotFound() {
        assertThrows(CarreraNoEncontradaException.class, () -> carreraDao.eliminarCarrera(99));
    }

    @Test
    void testObtenerMateriaCarrera() {
        Materia materia = new Materia("Programación", 1, new Profesor("Juan", "Perez", "Ing."));
        Carrera carrera = new Carrera("Sistemas", 5, 3, new HashMap<>());
        carrera.getMateriaList().put(1, materia);

        int id = carreraDao.crearCarrera(carrera);
        List<Materia> materias = carreraDao.obtenerMateria_Carrera(id);

        assertEquals(1, materias.size());
        assertEquals("Programación", materias.get(0).getNombreMateria());
    }

    @Test
    void testObtenerMateriaCarreraNotFound() {
        assertThrows(CarreraNoEncontradaException.class, () -> carreraDao.obtenerMateria_Carrera(50));
    }

    @Test
    void testObtenerMateriaCarreraSinMaterias() {
        int id = carreraDao.crearCarrera(new Carrera("Abogacía", 8, 4, new HashMap<>()));
        assertThrows(MateriaNoEncontradaException.class, () -> carreraDao.obtenerMateria_Carrera(id));
    }

    @Test
    void testObtenerCarreraDepartamento() {
        carreraDao.crearCarrera(new Carrera("Arquitectura", 6, 10));
        carreraDao.crearCarrera(new Carrera("Diseño", 4, 10));

        List<Carrera> carreras = carreraDao.obtenerCarrera_Departamento(10);

        assertEquals(2, carreras.size());
        assertTrue(carreras.get(0).getNombre().compareTo(carreras.get(1).getNombre()) <= 0);
    }

    @Test
    void testActualizarCarrera() {
        int id = carreraDao.crearCarrera(new Carrera("Odontología", 6, 8));
        Carrera actualizada = new Carrera("Odontología Avanzada", 7, 8);

        carreraDao.actualizarCarrera(id, actualizada);

        assertEquals("Odontología Avanzada", CarreraDaoImpl.listaCarreras.get(id).getNombre());
    }
}
