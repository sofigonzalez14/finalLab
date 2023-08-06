package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.CarreraBusinessImpl;
import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl;
import com.finalLaboIII.demo.Persistence.exceptions.CarreraNoEncontradaException;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
    private static final Logger logger = LoggerFactory.getLogger(CarreraController.class);
    Map<Integer, Carrera> listaCarreras = CarreraDaoImpl.listaCarreras;
    CarreraBusinessImpl carrerabsn = new CarreraBusinessImpl();
    @PostMapping
    public ResponseEntity<?> crearCarrera(@RequestBody Carrera carrera){
      if(listaCarreras.containsKey(carrera)){
          return ResponseEntity.badRequest().build();
      }
        return ResponseEntity.ok(carrerabsn.crearCarrera(carrera) + " carrera agregada correctamente");
    }
    @DeleteMapping("/{idCarrera}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer idCarrera){
        try{
            carrerabsn.eliminarCarrera(idCarrera);
        }catch (CarreraNoEncontradaException c){
            return ResponseEntity.badRequest().build();
        }
            return ResponseEntity.ok("carrera eliminada");
    }

    @GetMapping("/{idCarrera}/materias")
    public ResponseEntity<?> obtenerMateriaCarrera (@PathVariable Integer idCarrera){
        try{
            carrerabsn.obtenerMateria_Carrera(idCarrera);
        }catch(MateriaNoEncontradaException m ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(idCarrera);
    }

    @GetMapping("/carrera?{idDepartamento}")
    public ResponseEntity<?> obtenerCarrerasPorDepartamento(@RequestParam Integer idDepartamento) {
        try{
            carrerabsn.obtenerCarrera_Departamento(idDepartamento);
        }catch (CarreraNoEncontradaException c){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(idDepartamento);

    }

    @PutMapping("/{idCarrera}")
    public ResponseEntity<String> ActualizarCarrera (@PathVariable Integer idCarrera, @RequestBody Carrera carrera){
        try{
            carrerabsn.actualizarCarrera(idCarrera, carrera);
        }catch (CarreraNoEncontradaException c){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Nombre de la carrera actualizado correctamente");
    }
}
