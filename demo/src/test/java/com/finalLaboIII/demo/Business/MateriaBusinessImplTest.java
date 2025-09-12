package com.finalLaboIII.demo.Business;

import com.finalLaboIII.demo.Business.impl.MateriaBusinessImpl;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Model.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MateriaBusinessImplTest {

    private MateriaBusinessImpl materiaBusiness;

    @BeforeEach
    void setUp() {
        materiaBusiness = new MateriaBusinessImpl();
    }

    @Test
    void testCrearMateria() {
        Profesor profe = new Profesor("Juan", "Pérez", "Ingeniero",1);
        Materia materia = new Materia("Programación", 1, profe,2);

        int id = materiaBusiness.crearMateria(materia);

        assertTrue(id > 0);
        assertNotNull(materiaBusiness.obtenerMateriaPorId(id));
    }
}

