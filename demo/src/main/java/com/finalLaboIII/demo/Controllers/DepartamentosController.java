package com.finalLaboIII.demo.Controllers;

import com.finalLaboIII.demo.Business.impl.DepartamentosBusinessImpl;
import com.finalLaboIII.demo.Model.Alumno;
import com.finalLaboIII.demo.Model.Departamentos;
import com.finalLaboIII.demo.Persistence.exceptions.AlumnoNoEncontradoException;
import com.finalLaboIII.demo.Persistence.exceptions.DepartamentoNoEncontradoException;
import com.finalLaboIII.demo.Persistence.exceptions.NohayMateriasException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departamento")
public class DepartamentosController {
    DepartamentosBusinessImpl departamentobsn = new DepartamentosBusinessImpl();
    @PostMapping
    public ResponseEntity<Integer> crearDepto(@RequestBody Departamentos departamentos) {
        if (departamentos == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(departamentobsn.crearDepartamento(departamentos));
    }

    @DeleteMapping("/{idDepartamento}")
    public ResponseEntity<?> eliminarDepartamento (@PathVariable Integer idDepartamento) {
        try{
            departamentobsn.eliminarDepartamento(idDepartamento);
        }catch (DepartamentoNoEncontradoException d){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Departamento eliminado");
    }
    @GetMapping("/{idDepartamento}")
    public ResponseEntity<String> buscarDeptobyNombre (@RequestParam String nombreDpto){
        try{
            departamentobsn.buscarDepartamentobyNombre(nombreDpto);
        }catch(DepartamentoNoEncontradoException m){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("departamento: " + nombreDpto);
    }
}
