package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.dtos.EstadoAsignaturaDto;
import com.finalLaboIII.demo.Business.impl.AlumnoBusinessImpl;
import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.dtos.InscripcionCarreraDto;
import com.finalLaboIII.demo.Persistence.exceptions.AlumnoNoEncontradoException;
import com.finalLaboIII.demo.Persistence.exceptions.AsignaturaNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoBusinessImpl alumnobsn;

    public AlumnoController(AlumnoBusinessImpl alumnobsn) {
        this.alumnobsn = alumnobsn;
    }

    // Crear alumno
    @PostMapping
    public ResponseEntity<Integer> crearAlumno(@RequestBody Alumno alumno) {
        if (alumno == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnobsn.crearAlumno(alumno));
    }

    // Eliminar alumno
    @DeleteMapping("/{idAlumno}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer idAlumno) {
        try {
            alumnobsn.eliminarAlumno(idAlumno);
            return ResponseEntity.ok("Alumno eliminado");
        } catch (AlumnoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
        }
    }

    // Actualizar alumno
    @PutMapping("/{idAlumno}")
    public ResponseEntity<?> actualizarAlumno(@RequestBody Alumno alumno, @PathVariable Integer idAlumno) {
        try {
            alumnobsn.actualizarAlumno(idAlumno, alumno);
            return ResponseEntity.ok("Alumno actualizado correctamente");
        } catch (AlumnoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
        }
    }

    // Inscribir alumno en una carrera
    @PutMapping("/{idAlumno}/inscripcion")
    public ResponseEntity<?> inscribirAlumno(@PathVariable Integer idAlumno, @RequestBody InscripcionCarreraDto dto) {
        try {
            alumnobsn.inscribirAlumno(idAlumno, dto.getCarreraId());
            return ResponseEntity.ok("Alumno inscripto correctamente.");
        } catch (AlumnoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
        }
    }

    // Agregar asignatura a un alumno
    @PostMapping("/{idAlumno}/asignatura")
    public ResponseEntity<?> agregarAsignatura(@PathVariable Integer idAlumno, @RequestBody Integer idMateria) {
        try {
            alumnobsn.agregarAsignatura(idAlumno, idMateria);
            return ResponseEntity.ok("Asignatura agregada al alumno");
        } catch (AlumnoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
        }
    }

    // Modificar estado de una asignatura de un alumno
    @PutMapping("/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<?> modificarEstadoAsignatura(@PathVariable Integer idAlumno,
                                                       @PathVariable Integer idAsignatura,
                                                       @RequestBody EstadoAsignaturaDto estadoDto) {
        try {
            alumnobsn.modificarEstadoAsignatura(idAlumno, idAsignatura, estadoDto.getEstado(), estadoDto.getNota());
            return ResponseEntity.ok("Asignatura actualizada correctamente");
        } catch (AsignaturaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asignatura no encontrada");
        }
    }
}
