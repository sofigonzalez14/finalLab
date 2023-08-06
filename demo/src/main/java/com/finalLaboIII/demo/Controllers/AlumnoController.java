package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.dtos.EstadoAsignaturaDto;
import com.finalLaboIII.demo.Business.impl.AlumnoBusinessImpl;
import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Persistence.exceptions.AlumnoNoEncontradoException;
import com.finalLaboIII.demo.Persistence.exceptions.AsignaturaNoEncontradaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    AlumnoBusinessImpl Alumnobsn = new AlumnoBusinessImpl();

    @PostMapping
    public ResponseEntity<Integer> crearAlumno(@RequestBody Alumno alumno) {
        if (alumno == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(Alumnobsn.crearAlumno(alumno));
    }

    @DeleteMapping("/{idAlumno}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer idAlumno) {
        try {
            Alumnobsn.eliminarAlumno(idAlumno);
        } catch (AlumnoNoEncontradoException a) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Alumno eliminado");
    }

    @PutMapping("{idAlumno}")
    public ResponseEntity<String> ingresarActualizarAlumno(@RequestBody Alumno alumno, @PathVariable Integer idAlumno) {
        try {
            Alumnobsn.actualizarAlumno(idAlumno, alumno);
        } catch (AlumnoNoEncontradoException a) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("actualizado correctamente");
    }
    //inscribir alumno a una carrera

    @PutMapping("/alumno/{idAlumno}")
    public ResponseEntity<?> incribirAlumno(@PathVariable Integer idAlumno, @RequestBody Integer idCarrera) {
        try {
            Alumnobsn.inscribirAlumno(idAlumno, idCarrera);
        } catch (AlumnoNoEncontradoException a) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("alumno inscripto correctamente.");

    }

    @PostMapping("/alumno/{idAlumno}/asignatura/")
    public ResponseEntity<Integer> agregarAsignatura(@PathVariable Integer idAlumno, @RequestBody Integer idMateria) {
        try {
            Alumnobsn.agregarAsignatura(idAlumno, idMateria);
        } catch (AlumnoNoEncontradoException a) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(idAlumno);
    }


    @PutMapping("/alumno/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<Integer> modificarEstadoAsignatura (@PathVariable Integer idAlumno, @PathVariable Integer idAsignatura, @RequestBody EstadoAsignaturaDto estadodto){
        try{
            Alumnobsn.modificarEstadoAsignatura(idAlumno, idAsignatura, estadodto.getEstado(), estadodto.getNota());
        }catch (AsignaturaNoEncontradaException a){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(idAsignatura);
    }

}