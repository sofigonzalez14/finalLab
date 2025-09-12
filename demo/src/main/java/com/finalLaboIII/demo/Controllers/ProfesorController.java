package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.ProfesorBusinessImpl;
import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.exceptions.ProfesorNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    private final ProfesorBusinessImpl profesorbsn;

    public ProfesorController(ProfesorBusinessImpl profesorbsn) {
        this.profesorbsn = profesorbsn;
    }

    // Crear profesor
    @PostMapping
    public ResponseEntity<?> crearProfesor(@RequestBody Profesor profesor) {
        if (profesor == null) {
            return ResponseEntity.badRequest().build();
        }

        int id = profesorbsn.crearProfesor(profesor); // el DAO te devuelve el id
        profesor.setId(id);

        String mensaje = "Profesor creado con éxito. ID = " + id;
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    // Eliminar profesor por ID
    @DeleteMapping("/{idProfesor}")
    public ResponseEntity<?> eliminarProfesorById(@PathVariable Integer idProfesor) {
        try {
            profesorbsn.eliminarProfesor(idProfesor);
            return ResponseEntity.ok("Profesor eliminado correctamente");
        } catch (ProfesorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profesor no encontrado");
        }
    }

    // Obtener profesor por ID
    @GetMapping("/{idProfesor}")
    public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Integer idProfesor) {
        try {
            Profesor profesor = profesorbsn.obtenerProfesorPorId(idProfesor);
            return ResponseEntity.ok(profesor);
        } catch (ProfesorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profesor no encontrado");
        }
    }


    // Obtener profesor por nombre
    @GetMapping
    public ResponseEntity<?> obtenerProfesorPorNombre(@RequestParam String nombre) {
        try {
            profesorbsn.obtenerProfesor(nombre); // lo implementaremos en Business
            return ResponseEntity.ok("Profesor encontrado: " + nombre);
        } catch (ProfesorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profesor no encontrado");
        }
    }
}

