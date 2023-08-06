package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.ProfesorBusinessImpl;
import com.finalLaboIII.demo.Model.Profesor;
import com.finalLaboIII.demo.Persistence.exceptions.ProfesorNoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    ProfesorBusinessImpl profesorbsn = new ProfesorBusinessImpl();

    @PostMapping
    public ResponseEntity<?> crearProfesor (@RequestBody Profesor profesor){
        if(profesor == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(profesorbsn.crearProfesor(profesor));
    }
    @DeleteMapping("/profesor/{idProfesor}")
    public ResponseEntity<?> eliminarProfesorbyId (@PathVariable Integer idProfesor){
        try{
            profesorbsn.eliminarProfesor(idProfesor);
        }catch(ProfesorNoEncontradoException p){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Profesor eliminado correctamente.");
    }
    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<?> obtenerProfesor (@RequestParam String nombre){
        try{
            profesorbsn.obtenerProfesor(nombre);
        }catch(ProfesorNoEncontradoException p){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Profesor: " + nombre);
    }
}
