package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.MateriaBusinessImpl;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Model.Profesor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MateriaController.class)
class MateriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MateriaBusinessImpl materiaBusiness;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearMateria() throws Exception {
        Profesor profesor = new Profesor("Sofia", "Gomez", "Ing.", 1);
        Materia materia = new Materia("Laboratorio", 2, profesor, 1);

        when(materiaBusiness.crearMateria(any(Materia.class))).thenReturn(1);

        mockMvc.perform(post("/materia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materia)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreMateria").value("Laboratorio"));

        verify(materiaBusiness, times(1)).crearMateria(any(Materia.class));
    }



}

