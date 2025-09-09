package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.MateriaBusinessImpl;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private final MateriaBusinessImpl materiabsn;

    public MateriaController(MateriaBusinessImpl materiabsn) {
        this.materiabsn = materiabsn;
    }

    // Crear materia
    @PostMapping
    public ResponseEntity<Integer> crearMateria(@RequestBody Materia materia) {
        if (materia == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(materiabsn.crearMateria(materia));
    }

    // Eliminar materia
    @DeleteMapping("/{idMateria}")
    public ResponseEntity<?> eliminarMateria(@PathVariable Integer idMateria) {
        try {
            materiabsn.eliminarMateria(idMateria);
            return ResponseEntity.ok("Materia eliminada");
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia no encontrada");
        }
    }

    // Obtener materia por ID
    @GetMapping("/{idMateria}")
    public ResponseEntity<?> obtenerMateriaPorId(@PathVariable Integer idMateria) {
        try {
            materiabsn.obtenerMateriaPorId(idMateria); // luego lo implementamos en Business
            return ResponseEntity.ok("Materia encontrada con ID: " + idMateria);
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia no encontrada");
        }
    }

    // Obtener materia por nombre
    @GetMapping
    public ResponseEntity<?> obtenerMateriaPorNombre(@RequestParam String nombre) {
        try {
            materiabsn.obtenerMateria(nombre); // luego arreglamos en Business
            return ResponseEntity.ok("Materia encontrada: " + nombre);
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia no encontrada");
        }
    }

    // Actualizar materia
    @PutMapping("/{idMateria}")
    public ResponseEntity<?> actualizarMateria(@PathVariable Integer idMateria, @RequestBody Materia materia) {
        try {
            materiabsn.actualizarMateria(idMateria, materia);
            return ResponseEntity.ok("Materia actualizada correctamente");
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia no encontrada");
        }
    }
}

