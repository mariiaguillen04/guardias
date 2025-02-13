package com.guardias.web.controllers;


import com.guardias.persintence.entity.Anexo;
import com.guardias.service.AnexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/anexo")

public class AnexoController {
    @Autowired
    private AnexoService anexoService;

    //Recurso listado de todos los anexos
    @GetMapping
    public ResponseEntity<List<Anexo>> getAnexos(){return ResponseEntity.ok(this.anexoService.findAll());}

    //Recurso que devuelve un Anexo por id
    @GetMapping("/{idAnexo}")
    public ResponseEntity<Anexo> getAnexoById(@PathVariable int idAnexo){
        Optional<Anexo> anexo = this.anexoService.findById(idAnexo);
        if(anexo.isPresent()){
            return ResponseEntity.ok(anexo.get());
        }
            return ResponseEntity.notFound().build();
    }

    //Recurso para crear un Anexo
    @PostMapping
    public ResponseEntity<Anexo> createAnexo(@RequestBody Anexo anexo){
        return ResponseEntity.ok(this.anexoService.create(anexo));
    }

    //Recurso para actualizar un determinado Anexo
    @PutMapping("/{idAnexo}")
    public ResponseEntity<Anexo> updateAnexo(@RequestBody Anexo anexo,@PathVariable int idAnexo) {
        if (this.anexoService.existsAnexo(idAnexo)) {
            return ResponseEntity.ok(this.anexoService.create(anexo));
        }
        return ResponseEntity.notFound().build();
    }

    //Recurso para eliminar un Anexo determinado
    @DeleteMapping("/{idAnexo}")
    public ResponseEntity<Anexo> deleteAnexo(@PathVariable int idAnexo){
        if (this.anexoService.delete(idAnexo)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
