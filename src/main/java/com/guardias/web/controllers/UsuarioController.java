package com.guardias.web.controllers;

import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.repository.UsuarioRepository;
import com.guardias.service.UsuarioService;
import com.guardias.service.dto.UsuarioDTO;
import com.guardias.service.mappers.UsuarioMapper;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        return ResponseEntity.ok(this.usuarioService.findAll());
    }


    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable int idUsuario){

        Usuario usuario = this.usuarioService.findById(idUsuario);
        if(this.usuarioService.existUsuario(idUsuario)){
            return ResponseEntity.ok(usuarioMapper.toDTO(usuario));
        }
       return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(this.usuarioService.create(usuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable int idUsuario){
        if(this.usuarioService.existUsuario(idUsuario)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable int idUsuario){
        if(this.usuarioService.delete(idUsuario)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
