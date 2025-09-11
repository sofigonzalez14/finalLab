package com.finalLaboIII.demo.Business;

import com.finalLaboIII.demo.Business.impl.ProfesorBusinessImpl;
import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.Impl.ProfesorDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfesorBusinessImplTest {

    private ProfesorDaoImpl profesorDaoMock;
    private ProfesorBusinessImpl profesorBusiness;

    @BeforeEach
    void setUp() {
        profesorDaoMock = Mockito.mock(ProfesorDaoImpl.class);
        profesorBusiness = new ProfesorBusinessImpl() {
            @Override
            public int crearProfesor(Profesor profesor) {
                return profesorDaoMock.crearProfesor(profesor);
            }

            @Override
            public void eliminarProfesor(Integer idProfesor) {
                profesorDaoMock.eliminarProfesor(idProfesor);
            }

            @Override
            public Profesor obtenerProfesor(String nombreProfesor) {
                return profesorDaoMock.obtenerProfesor(nombreProfesor);
            }

            @Override
            public Profesor obtenerProfesorPorId(Integer idProfesor) {
                return profesorDaoMock.obtenerProfesorPorId(idProfesor);
            }
        };
    }

    @Test
    void testCrearProfesorDelegadoAlDao() {
        Profesor profesor = new Profesor("Luis", "Méndez", "Doctor");
        when(profesorDaoMock.crearProfesor(profesor)).thenReturn(1);

        int id = profesorBusiness.crearProfesor(profesor);

        assertEquals(1, id);
        verify(profesorDaoMock, times(1)).crearProfesor(profesor);
    }
}


