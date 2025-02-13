package com.guardias.web.controllers;

import com.guardias.persintence.entity.Anexo;
import com.guardias.persintence.entity.Falta;
import com.guardias.service.FaltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/falta")
public class FaltaController {
    @Autowired
    private FaltaService faltaService;

    //Recurso listado de todos las faltas
    @GetMapping
    public ResponseEntity<List<Falta>> getFaltas(){return ResponseEntity.ok(this.faltaService.findAll());}

    //Recurso que devuelve un Falta por id
    @GetMapping("/{idFalta}")
    public ResponseEntity<Falta> getFaltaById(@PathVariable int idFalta){
        Optional<Falta> falta = this.faltaService.findById(idFalta);
        if(falta.isPresent()){
            return ResponseEntity.ok(falta.get());
        }
        return ResponseEntity.notFound().build();
    }

    //Recurso para crear un Falta
    @PostMapping
    public ResponseEntity<Falta> createFalta(@RequestBody Falta falta){
        return ResponseEntity.ok(this.faltaService.create(falta));
    }

    //Recurso para actualizar una determinada Falta
    @PutMapping("/{idFalta}")
    public ResponseEntity<Falta> updateFalta(@RequestBody Falta falta,@PathVariable int idFalta) {
        if (this.faltaService.existsFalta(idFalta)) {
            return ResponseEntity.ok(this.faltaService.create(falta));
        }
        return ResponseEntity.notFound().build();
    }

    //Recurso para eliminar una Falta determinada
    @DeleteMapping("/{idFalta}")
    public ResponseEntity<Falta> deleteFalta(@PathVariable int idFalta){
        if (this.faltaService.delete(idFalta)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
