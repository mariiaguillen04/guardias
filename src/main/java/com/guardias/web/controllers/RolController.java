package com.guardias.web.controllers;

import com.guardias.persintence.entity.Rol;
;
import com.guardias.service.RolService;
import com.guardias.service.dto.RolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    //Recurso listado de todos los roles
    @GetMapping
    public ResponseEntity<List<RolDTO>> getRoles(){return ResponseEntity.ok(this.rolService.findAll());}

    //Recurso que devuelve un Rol por id
    @GetMapping("/{idRol}")
    public ResponseEntity<Rol> getRolById(@PathVariable int idRol){
        Rol rol = this.rolService.findById(idRol);
        if(rolService.existsRol(idRol)){
            return ResponseEntity.ok(rol);
        }
        return ResponseEntity.notFound().build();
    }

    //Recurso para crear un Rol
    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol){
        return ResponseEntity.ok(this.rolService.create(rol));
    }

    //Recurso para actualizar un determinado Rol
    @PutMapping("/{idRol}")
    public ResponseEntity<Rol> updateRol(@RequestBody Rol rol,@PathVariable int idRol) {
        if (this.rolService.existsRol(idRol)) {
            return ResponseEntity.ok(this.rolService.create(rol));
        }
        return ResponseEntity.notFound().build();
    }

    //Recurso para eliminar un Rol determinado
    @DeleteMapping("/{idRol}")
    public ResponseEntity<Rol> deleteRol(@PathVariable int idRol){
        if (this.rolService.delete(idRol)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
