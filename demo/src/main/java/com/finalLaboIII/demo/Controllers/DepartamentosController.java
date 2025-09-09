package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.DepartamentosBusinessImpl;
import com.finalLaboIII.demo.Model.Departamentos;
import com.finalLaboIII.demo.Persistence.exceptions.DepartamentoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departamento")
public class DepartamentosController {

    private final DepartamentosBusinessImpl departamentobsn;

    public DepartamentosController(DepartamentosBusinessImpl departamentobsn) {
        this.departamentobsn = departamentobsn;
    }

    // Crear departamento
    @PostMapping
    public ResponseEntity<Integer> crearDepto(@RequestBody Departamentos departamentos) {
        if (departamentos == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentobsn.crearDepartamento(departamentos));
    }

    // Eliminar departamento
    @DeleteMapping("/{idDepartamento}")
    public ResponseEntity<?> eliminarDepartamento(@PathVariable Integer idDepartamento) {
        try {
            departamentobsn.eliminarDepartamento(idDepartamento);
            return ResponseEntity.ok("Departamento eliminado");
        } catch (DepartamentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }

    // Obtener departamento por ID
    @GetMapping("/{idDepartamento}")
    public ResponseEntity<?> buscarDeptoPorId(@PathVariable Integer idDepartamento) {
        try {
            departamentobsn.buscarDepartamentobyId(idDepartamento); // lo implementaremos en Business
            return ResponseEntity.ok("Departamento encontrado con ID: " + idDepartamento);
        } catch (DepartamentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }

    // Obtener departamento por nombre
    @GetMapping
    public ResponseEntity<?> buscarDeptoPorNombre(@RequestParam String nombreDpto) {
        try {
            departamentobsn.buscarDepartamentobyNombre(nombreDpto); // lo implementaremos en Business
            return ResponseEntity.ok("Departamento encontrado: " + nombreDpto);
        } catch (DepartamentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }
}

