package com.guardias.web.controllers;

import com.guardias.persintence.entity.Rol;
import com.guardias.persintence.entity.Tramo;
import com.guardias.service.RolService;
import com.guardias.service.TramoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tramo")
public class TramoController {
    @Autowired
    private TramoService tramoService;

    //Recurso listado de todos los tramos
    @GetMapping
    public ResponseEntity<List<Tramo>> getTramos(){return ResponseEntity.ok(this.tramoService.findAll());}

    //Recurso que devuelve un Tramo por id
    @GetMapping("/{idTramo}")
    public ResponseEntity<Tramo> getTramoById(@PathVariable int idTramo){
        Optional<Tramo> tramo = this.tramoService.findById(idTramo);
        if(tramo.isPresent()){
            return ResponseEntity.ok(tramo.get());
        }
        return ResponseEntity.notFound().build();
    }

    //Recurso para crear un Tramo
    @PostMapping
    public ResponseEntity<Tramo> createTramo(@RequestBody Tramo tramo){
        return ResponseEntity.ok(this.tramoService.create(tramo));
    }

    //Recurso para actualizar un determinado Tramo
    @PutMapping("/{idTramo}")
    public ResponseEntity<Tramo> updateTramo(@RequestBody Tramo tramo,@PathVariable int idTramo) {
        if (this.tramoService.existsTramo(idTramo)) {
            return ResponseEntity.ok(this.tramoService.create(tramo));
        }
        return ResponseEntity.notFound().build();
    }

    //Recurso para eliminar un Tramo determinado
    @DeleteMapping("/{idTramo}")
    public ResponseEntity<Tramo> deleteTramo(@PathVariable int idTramo){
        if (this.tramoService.delete(idTramo)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
