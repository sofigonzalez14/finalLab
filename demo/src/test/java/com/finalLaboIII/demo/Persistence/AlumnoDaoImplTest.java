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

        int id = alumnoDao.crearAlumno(alumno);
        Alumno obtenido = alumnoDao.obtenerAlumno(id);

        assertEquals("Carla", obtenido.getNombre());
        assertEquals("Martinez", obtenido.getApellido());
        assertEquals(50222111, obtenido.getDni());
    }

    @Test
    void testEliminarAlumno() {
        Alumno alumno = new Alumno("Lucas", "Fernandez", 49999888);
        int id = alumnoDao.crearAlumno(alumno);

        alumnoDao.eliminarAlumno(id);

        assertThrows(AlumnoNoEncontradoException.class, () -> alumnoDao.obtenerAlumno(id));
    }

    @Test
    void testActualizarAlumno() {
        Alumno alumno = new Alumno("Ana", "Lopez", 40011122);
        int id = alumnoDao.crearAlumno(alumno);

        Alumno actualizado = new Alumno("Ana Maria", "Lopez", 40011122);
        alumnoDao.actualizarAlumno(id, actualizado);

        Alumno obtenido = alumnoDao.obtenerAlumno(id);
        assertEquals("Ana Maria", obtenido.getNombre());
    }

    @Test
    void testInscribirAlumnoConExcesoDeCarreras() {
        Alumno alumno = new Alumno("Pedro", "Gomez", 42233444);
        int id = alumnoDao.crearAlumno(alumno);

        // ⚠️ IMPORTANTE: limpiar carreras y crear carreras válidas
        com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl.listaCarreras.clear();
        com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl carreraDao = new com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl();

        int idCarrera1 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera1", 8, 1));
        int idCarrera2 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera2", 8, 1));
        int idCarrera3 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera3", 8, 1));
        int idCarrera4 = carreraDao.crearCarrera(new com.finalLaboIII.demo.Model.Carrera("Carrera4", 8, 1));

        // inscribir en 3 carreras
        alumnoDao.inscribirAlumno(id, idCarrera1);
        alumnoDao.inscribirAlumno(id, idCarrera2);
        alumnoDao.inscribirAlumno(id, idCarrera3);

        // la cuarta debe lanzar LimiteDeCarrerasException
        assertThrows(LimiteDeCarrerasException.class, () -> alumnoDao.inscribirAlumno(id, idCarrera4));
    }


    @Test
    void testAgregarAsignaturaYModificarEstado() {
        Alumno alumno = new Alumno("Marta", "Perez", 45566777);
        int id = alumnoDao.crearAlumno(alumno);

        alumnoDao.agregarAsignatura(id, 101);

        // comprobar que la asignatura fue agregada
        assertTrue(alumnoDao.obtenerAlumno(id).getListaAsignatura().containsKey(101));

        // modificar estado y nota
        alumnoDao.modificarEstadoAsignatura(id, 101, Asignatura.EstadoAsignatura.CURSADA, 8);

        Asignatura asignatura = alumnoDao.obtenerAlumno(id).getListaAsignatura().get(101);
        assertEquals(Asignatura.EstadoAsignatura.CURSADA, asignatura.getEstado());
        assertEquals(8, asignatura.getNota());
    }

    @Test
    void testModificarEstadoAsignaturaInvalido() {
        Alumno alumno = new Alumno("Diego", "Suarez", 43322111);
        int id = alumnoDao.crearAlumno(alumno);

        alumnoDao.agregarAsignatura(id, 202);

        // nota inválida (<= 0)
        assertThrows(NotaInvalidaException.class, () -> alumnoDao.modificarEstadoAsignatura(id, 202, Asignatura.EstadoAsignatura.CURSADA, 0));

        // aprobar con nota < 4
        assertThrows(EstadoInvalidoException.class, () -> alumnoDao.modificarEstadoAsignatura(id, 202, Asignatura.EstadoAsignatura.APROBADA, 3));
    }
}
