package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.CarreraBusinessImpl;
import com.finalLaboIII.demo.dtos.CarreraDto;
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

@WebMvcTest(CarreraController.class)
class CarreraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarreraBusinessImpl carreraBusiness;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearCarrera() throws Exception {
        CarreraDto dto = new CarreraDto("Ingeniería", 10, 1);

        when(carreraBusiness.crearCarrera(any())).thenReturn(1);

        mockMvc.perform(post("/carrera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Carrera creada con id: 1"));

        verify(carreraBusiness, times(1)).crearCarrera(any());
    }
}
