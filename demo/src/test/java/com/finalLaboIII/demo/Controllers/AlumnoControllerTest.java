package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.AlumnoBusinessImpl;
import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.dtos.EstadoAsignaturaDto;
import com.finalLaboIII.demo.dtos.InscripcionCarreraDto;
import com.finalLaboIII.demo.Persistence.exceptions.AlumnoNoEncontradoException;
import com.finalLaboIII.demo.Persistence.exceptions.AsignaturaNoEncontradaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlumnoController.class)
class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoBusinessImpl alumnoBusiness;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCrearAlumno() throws Exception {
        Alumno alumno = new Alumno("Juan", "Perez", 12345678);
        alumno.setId(1); // simulamos que se guardó con ID 1

        when(alumnoBusiness.crearAlumno(any())).thenReturn(alumno);

        mockMvc.perform(post("/alumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumno)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.dni").value(12345678));

        verify(alumnoBusiness, times(1)).crearAlumno(any(Alumno.class));
    }


    @Test
    void testEliminarAlumnoOk() throws Exception {
        doNothing().when(alumnoBusiness).eliminarAlumno(1);

        mockMvc.perform(delete("/alumno/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Alumno eliminado"));

        verify(alumnoBusiness, times(1)).eliminarAlumno(1);
    }

    @Test
    void testEliminarAlumnoNotFound() throws Exception {
        doThrow(new AlumnoNoEncontradoException("Alumno no encontrado"))
                .when(alumnoBusiness).eliminarAlumno(99);

        mockMvc.perform(delete("/alumno/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Alumno no encontrado"));
    }

    @Test
    void testActualizarAlumno() throws Exception {
        Alumno alumno = new Alumno("Laura", "Gomez", 87654321);

        doNothing().when(alumnoBusiness).actualizarAlumno(eq(1), any(Alumno.class));

        mockMvc.perform(put("/alumno/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumno)))
                .andExpect(status().isOk())
                .andExpect(content().string("Alumno actualizado correctamente"));
    }

    @Test
    void testInscribirAlumno() throws Exception {
        InscripcionCarreraDto dto = new InscripcionCarreraDto();
        dto.setCarreraId(10);

        doNothing().when(alumnoBusiness).inscribirAlumno(1, 10);

        mockMvc.perform(put("/alumno/1/inscripcion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Alumno inscripto correctamente."));
    }

    @Test
    void testAgregarAsignatura() throws Exception {
        doNothing().when(alumnoBusiness).agregarAsignatura(1, 200);

        mockMvc.perform(post("/alumno/1/asignatura")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200"))
                .andExpect(status().isOk())
                .andExpect(content().string("Asignatura agregada al alumno"));
    }

    @Test
    void testModificarEstadoAsignaturaOk() throws Exception {
        EstadoAsignaturaDto dto = new EstadoAsignaturaDto(
                com.finalLaboIII.demo.Model.Asignatura.EstadoAsignatura.CURSADA, 7
        );

        doNothing().when(alumnoBusiness).modificarEstadoAsignatura(1, 200, dto.getEstado(), dto.getNota());

        mockMvc.perform(put("/alumno/1/asignatura/200")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Asignatura actualizada correctamente"));
    }

    @Test
    void testModificarEstadoAsignaturaNotFound() throws Exception {
        EstadoAsignaturaDto dto = new EstadoAsignaturaDto(
                com.finalLaboIII.demo.Model.Asignatura.EstadoAsignatura.CURSADA, 7
        );

        doThrow(new AsignaturaNoEncontradaException("Asignatura no encontrada"))
                .when(alumnoBusiness).modificarEstadoAsignatura(1, 200, dto.getEstado(), dto.getNota());

        mockMvc.perform(put("/alumno/1/asignatura/200")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Asignatura no encontrada"));
    }
}

