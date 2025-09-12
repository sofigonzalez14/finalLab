package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.CarreraBusinessImpl;
import com.finalLaboIII.demo.dtos.CarreraDto;
import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Persistence.exceptions.CarreraNoEncontradaException;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import com.finalLaboIII.demo.dtos.CarreraResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Carrera carrera = new Carrera(
                dto.getNombre(),
                dto.getCantidadMaterias(),
                dto.getIdDepartamento(),
                dto.getDuracionAnios()
        );

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

    // Obtener todas las carreras o filtrar por departamento
    @GetMapping
    public ResponseEntity<?> obtenerCarreras(@RequestParam(required = false) Integer idDepartamento) {
        try {
            List<Carrera> carreras;

            if (idDepartamento != null) {
                carreras = carrerabsn.obtenerCarrera_Departamento(idDepartamento);
            } else {
                carreras = carrerabsn.listarTodas();
            }

            // mapear a DTOs
            List<CarreraResponseDto> response = carreras.stream()
                    .map(c -> new CarreraResponseDto(
                            c.getId(),
                            c.getNombre(),
                            c.getCantidadMaterias(),   // 👈 actualizado
                            c.getDuracionAnios()
                    ))
                    .toList();

            return ResponseEntity.ok(response);

        } catch (CarreraNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron carreras");
        }
    }

    // Obtener carrera por id
    @GetMapping("/{idCarrera}")
    public ResponseEntity<?> obtenerCarreraPorId(@PathVariable Integer idCarrera) {
        try {
            Carrera carrera = carrerabsn.obtenerCarrera(idCarrera);

            CarreraResponseDto response = new CarreraResponseDto(
                    carrera.getId(),
                    carrera.getNombre(),
                    carrera.getCantidadMaterias(),   // 👈 actualizado
                    carrera.getDuracionAnios()
            );

            return ResponseEntity.ok(response);

        } catch (CarreraNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrera no encontrada");
        }
    }

    // Actualizar carrera
    @PutMapping("/{idCarrera}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer idCarrera, @RequestBody CarreraDto dto) {
        try {
            Carrera carrera = new Carrera(
                    dto.getNombre(),
                    dto.getCantidadMaterias(),
                    dto.getIdDepartamento(),
                    dto.getDuracionAnios()
            );
            carrerabsn.actualizarCarrera(idCarrera, carrera);
            return ResponseEntity.ok("Carrera actualizada correctamente");
        } catch (CarreraNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrera no encontrada");
        }
    }
}

