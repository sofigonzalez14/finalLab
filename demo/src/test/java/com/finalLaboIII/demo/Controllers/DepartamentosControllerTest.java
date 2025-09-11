package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.DepartamentosBusinessImpl;
import com.finalLaboIII.demo.Model.Departamentos;
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

@WebMvcTest(DepartamentosController.class)
class DepartamentosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentosBusinessImpl business;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearDepartamento() throws Exception {
        Departamentos dpto = new Departamentos("Ciencias Naturales");

        when(business.crearDepartamento(any())).thenReturn(1);

        mockMvc.perform(post("/departamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dpto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));

        verify(business, times(1)).crearDepartamento(any());
    }

    @Test
    void testEliminarDepartamento() throws Exception {
        doNothing().when(business).eliminarDepartamento(1);

        mockMvc.perform(delete("/departamento/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Departamento eliminado"));

        verify(business, times(1)).eliminarDepartamento(1);
    }
}
