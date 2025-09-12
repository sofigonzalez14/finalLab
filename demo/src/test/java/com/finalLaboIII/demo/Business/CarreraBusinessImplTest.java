package com.finalLaboIII.demo.Business;

import com.finalLaboIII.demo.Business.impl.CarreraBusinessImpl;
import com.finalLaboIII.demo.Model.Carrera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarreraBusinessImplTest {

    private CarreraBusinessImpl carreraBusiness;

    @BeforeEach
    void setUp() {
        carreraBusiness = new CarreraBusinessImpl();
    }

    @Test
    void testCrearCarrera() {
        Carrera carrera = new Carrera("Ingeniería", 8, 1, 5);

    int id = carreraBusiness.crearCarrera(carrera);

        assertTrue(id > 0);
        assertNotNull(carreraBusiness.obtenerCarrera_Departamento(1));
    }
}


