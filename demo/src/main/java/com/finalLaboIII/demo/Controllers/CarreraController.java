package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.CarreraBusinessImpl;
import com.finalLaboIII.demo.dtos.CarreraDto;
import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Persistence.exceptions.CarreraNoEncontradaException;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    private final CarreraBusinessImpl carrerabsn;

    public CarreraController(CarreraBusinessImpl carrerabsn) {
        this.carrerabsn = carrerabsn;
    }

    // Crear carrera
    @PostMapping
    public ResponseEntity<?> crearCarrera(@RequestBody CarreraDto dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        Carrera carrera = new Carrera(dto.getNombre(), dto.getCantidadCuatrimestre(), dto.getIdDepartamento());
        Integer idCarrera = carrerabsn.crearCarrera(carrera);
        return ResponseEntity.status(HttpStatus.CREATED).body("Carrera creada con id: " + idCarrera);
    }

    // Eliminar carrera
    @DeleteMapping("/{idCarrera}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer idCarrera) {
        try {
            carrerabsn.eliminarCarrera(idCarrera);
            return ResponseEntity.ok("Carrera eliminada");
        } catch (CarreraNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrera no encontrada");
        }
    }

    // Obtener materias de una carrera
    @GetMapping("/{idCarrera}/materias")
    public ResponseEntity<?> obtenerMateriaCarrera(@PathVariable Integer idCarrera) {
        try {
            return ResponseEntity.ok(carrerabsn.obtenerMateria_Carrera(idCarrera));
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay materias en la carrera");
        }
    }

    // Obtener carreras de un departamento
    @GetMapping
    public ResponseEntity<?> obtenerCarrerasPorDepartamento(@RequestParam Integer idDepartamento) {
        try {
            return ResponseEntity.ok(carrerabsn.obtenerCarrera_Departamento(idDepartamento));
        } catch (CarreraNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron carreras en el departamento");
        }
    }

    // Actualizar carrera
    @PutMapping("/{idCarrera}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer idCarrera, @RequestBody CarreraDto dto) {
        try {
            Carrera carrera = new Carrera(dto.getNombre(), dto.getCantidadCuatrimestre(), dto.getIdDepartamento());
            carrerabsn.actualizarCarrera(idCarrera, carrera);
            return ResponseEntity.ok("Carrera actualizada correctamente");
        } catch (CarreraNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrera no encontrada");
        }
    }
}

