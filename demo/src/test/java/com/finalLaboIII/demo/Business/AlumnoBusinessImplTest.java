package com.finalLaboIII.demo.Business;

import com.finalLaboIII.demo.Business.impl.AlumnoBusinessImpl;
import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Asignatura;
import com.finalLaboIII.demo.Persistence.Impl.AlumnoDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class AlumnoBusinessImplTest {

    private AlumnoDaoImpl alumnoDao;
    private AlumnoBusinessImpl alumnoBusiness;

    @BeforeEach
    void setUp() {
        alumnoDao = Mockito.mock(AlumnoDaoImpl.class); // mockeamos la DAO
        alumnoBusiness = new AlumnoBusinessImpl() {
            // Sobrescribimos el DAO para inyectar el mock
            private final AlumnoDaoImpl mockDao = alumnoDao;

            @Override
            public int crearAlumno(Alumno alumno) {
                return mockDao.crearAlumno(alumno);
            }

            @Override
            public void eliminarAlumno(Integer idAlumno) {
                mockDao.eliminarAlumno(idAlumno);
            }

            @Override
            public void actualizarAlumno(int idAlumno, Alumno alumno) {
                mockDao.actualizarAlumno(idAlumno, alumno);
            }

            @Override
            public Alumno obtenerAlumno(Integer idAlumno) {
                return mockDao.obtenerAlumno(idAlumno);
            }

            @Override
            public void inscribirAlumno(Integer idAlumno, Integer idCarrera) {
                mockDao.inscribirAlumno(idAlumno, idCarrera);
            }

            @Override
            public void agregarAsignatura(Integer idAlumno, Integer idMateria) {
                mockDao.agregarAsignatura(idAlumno, idMateria);
            }

            @Override
            public void modificarEstadoAsignatura(Integer idAlumno, Integer idAsignatura,
                                                  Asignatura.EstadoAsignatura estado, Integer nota) {
                mockDao.modificarEstadoAsignatura(idAlumno, idAsignatura, estado, nota);
            }
        };
    }

    @Test
    void testCrearAlumnoDelegacion() {
        Alumno alumno = new Alumno("Mario", "Alvarez", 44556677);

        alumnoBusiness.crearAlumno(alumno);

        verify(alumnoDao, times(1)).crearAlumno(alumno);
    }

    @Test
    void testEliminarAlumnoDelegacion() {
        alumnoBusiness.eliminarAlumno(1);

        verify(alumnoDao, times(1)).eliminarAlumno(1);
    }

    @Test
    void testActualizarAlumnoDelegacion() {
        Alumno alumno = new Alumno("Laura", "Diaz", 40111222);

        alumnoBusiness.actualizarAlumno(1, alumno);

        verify(alumnoDao, times(1)).actualizarAlumno(1, alumno);
    }

    @Test
    void testObtenerAlumnoDelegacion() {
        alumnoBusiness.obtenerAlumno(1);

        verify(alumnoDao, times(1)).obtenerAlumno(1);
    }

    @Test
    void testInscribirAlumnoDelegacion() {
        alumnoBusiness.inscribirAlumno(1, 100);

        verify(alumnoDao, times(1)).inscribirAlumno(1, 100);
    }

    @Test
    void testAgregarAsignaturaDelegacion() {
        alumnoBusiness.agregarAsignatura(1, 200);

        verify(alumnoDao, times(1)).agregarAsignatura(1, 200);
    }

    @Test
    void testModificarEstadoAsignaturaDelegacion() {
        alumnoBusiness.modificarEstadoAsignatura(1, 200, Asignatura.EstadoAsignatura.CURSADA, 7);

        verify(alumnoDao, times(1)).modificarEstadoAsignatura(1, 200, Asignatura.EstadoAsignatura.CURSADA, 7);
    }
}
