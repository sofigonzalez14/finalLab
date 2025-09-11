package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.ProfesorBusinessImpl;
import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.exceptions.ProfesorNoEncontradoException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ProfesorController.class)
class ProfesorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfesorBusinessImpl profesorBusiness;

    @Test
    void testCrearProfesor() throws Exception {
        Profesor profesor = new Profesor("Mario", "Rossi", "Doctor");
        when(profesorBusiness.crearProfesor(Mockito.any(Profesor.class))).thenReturn(1);

        mockMvc.perform(post("/profesor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Mario\",\"apellido\":\"Rossi\",\"titulo\":\"Doctor\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    void testEliminarProfesorNotFound() throws Exception {
        // Simular que el business tira la excepción esperada
        doThrow(new ProfesorNoEncontradoException("Profesor no encontrado"))
                .when(profesorBusiness).eliminarProfesor(999);

        mockMvc.perform(delete("/profesor/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Profesor no encontrado"));
    }

}
