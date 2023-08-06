package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.MateriaBusinessImpl;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import com.finalLaboIII.demo.Persistence.exceptions.NohayMateriasException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public class MateriaController {
    MateriaBusinessImpl materiabsn = new MateriaBusinessImpl();
    @PostMapping
    public ResponseEntity<Integer> crearMateria (@RequestBody Materia materia){
        if(materia == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(materiabsn.crearMateria(materia));
    }

    @DeleteMapping("/{idMateria}")
    public ResponseEntity<?> eliminarMateria(@PathVariable Integer idMateria){
        try{
            materiabsn.eliminarMateria(idMateria);
        }catch(MateriaNoEncontradaException m){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Materia eliminada. ");
    }
    @GetMapping("{idMateria}/nombre")
    public ResponseEntity<String> obtenerMateriasbyNombre (@RequestParam String nombre){
        try{
            materiabsn.obtenerMateria(nombre);
        }catch(NohayMateriasException m){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("materia: " + nombre);
    }
    @PutMapping("/materia/{idMateria}")
    public ResponseEntity<?> actualizarMateria (@RequestBody Materia materia, @PathVariable Integer idMateria){
        try{
            materiabsn.actualizarMateria(idMateria, materia);
        }catch (MateriaNoEncontradaException m){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Materia actualizada");
    }

}
