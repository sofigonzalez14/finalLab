package com.finalLaboIII.demo.Persistence;

import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;
import com.finalLaboIII.demo.Persistence.Impl.AlumnoDaoImpl;
import com.finalLaboIII.demo.Persistence.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlumnoDaoImplTest {

    private AlumnoDaoImpl alumnoDao;

    @BeforeEach
    void setUp() {
        alumnoDao = new AlumnoDaoImpl();
        AlumnoDaoImpl.listaAlumno.clear(); // limpio la lista antes de cada test
    }

    @Test
    void testCrearYObtenerAlumno() {
        Alumno alumno = new Alumno("Carla", "Martinez", 50222111);

        Alumno creado = alumnoDao.crearAlumno(alumno);
        Alumno obtenido = alumnoDao.obtenerAlumno(creado.getId());

        assertEquals("Carla", obtenido.getNombre());
        assertEquals("Martinez", obtenido.getApellido());
        assertEquals(50222111, obtenido.getDni());
    }

    @Test
    void testEliminarAlumno() {
        Alumno alumno = new Alumno("Lucas", "Fernandez", 49999888);
        Alumno creado = alumnoDao.crearAlumno(alumno);

        alumnoDao.eliminarAlumno(creado.getId());

        assertThrows(AlumnoNoEncontradoException.class, () -> alumnoDao.obtenerAlumno(creado.getId()));
    }

    @Test
    void testActualizarAlumno() {
        Alumno alumno = new Alumno("Ana", "Lopez", 40011122);
        Alumno creado = alumnoDao.crearAlumno(alumno);

        Alumno actualizado = new Alumno("Ana Maria", "Lopez", 40011122);
        alumnoDao.actualizarAlumno(creado.getId(), actualizado);

        Alumno obtenido = alumnoDao.obtenerAlumno(creado.getId());
        assertEquals("Ana Maria", obtenido.getNombre());
    }

    @Test
    void testInscribirAlumnoConExcesoDeCarreras() {
        Alumno alumno = new Alumno("Pedro", "Gomez", 42233444);
        Alumno creado = alumnoDao.crearAlumno(alumno);

        // ⚠️ IMPORTANTE: limpiar carreras y crear carreras válidas
        com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl.listaCarreras.clear();
        com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl carreraDao = new com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl();

        int idCarrera1 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera1", 8, 1,4));
        int idCarrera2 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera2", 8, 1,4));
        int idCarrera3 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera3", 8, 1,5));
        int idCarrera4 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera4", 8, 1,5));

        // inscribir en 3 carreras
        alumnoDao.inscribirAlumno(creado.getId(), idCarrera1);
        alumnoDao.inscribirAlumno(creado.getId(), idCarrera2);
        alumnoDao.inscribirAlumno(creado.getId(), idCarrera3);

        // la cuarta debe lanzar LimiteDeCarrerasException
        assertThrows(LimiteDeCarrerasException.class, () -> alumnoDao.inscribirAlumno(creado.getId(), idCarrera4));
    }

    @Test
    void testAgregarAsignaturaYModificarEstado() {
        Alumno alumno = new Alumno("Marta", "Perez", 45566777);
        Alumno creado = alumnoDao.crearAlumno(alumno);

        alumnoDao.agregarAsignatura(creado.getId(), 101);

        // comprobar que la asignatura fue agregada
        assertTrue(alumnoDao.obtenerAlumno(creado.getId()).getListaAsignatura().containsKey(101));

        // modificar estado y nota
        alumnoDao.modificarEstadoAsignatura(creado.getId(), 101, Asignatura.EstadoAsignatura.CURSADA, 8);

        Asignatura asignatura = alumnoDao.obtenerAlumno(creado.getId()).getListaAsignatura().get(101);
        assertEquals(Asignatura.EstadoAsignatura.CURSADA, asignatura.getEstado());
        assertEquals(8, asignatura.getNota());
    }

    @Test
    void testModificarEstadoAsignaturaInvalido() {
        Alumno alumno = new Alumno("Diego", "Suarez", 43322111);
        Alumno creado = alumnoDao.crearAlumno(alumno);

        alumnoDao.agregarAsignatura(creado.getId(), 202);

        // nota inválida (<= 0)
        assertThrows(NotaInvalidaException.class, () -> alumnoDao.modificarEstadoAsignatura(creado.getId(), 202, Asignatura.EstadoAsignatura.CURSADA, 0));

        // aprobar con nota < 4
        assertThrows(EstadoInvalidoException.class, () -> alumnoDao.modificarEstadoAsignatura(creado.getId(), 202, Asignatura.EstadoAsignatura.APROBADA, 3));
    }
}
