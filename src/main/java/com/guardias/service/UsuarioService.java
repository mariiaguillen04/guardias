package com.guardias.service;

import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    public boolean existUsuario(int idUsuario){
       return this.usuarioRepository.existsById(idUsuario);
    }

    public Optional<Usuario> findById(int idUsuario){
        return this.usuarioRepository.findById(idUsuario);
    }

    public Usuario create(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    public boolean delete(int idUsuario){
        boolean result = false;

        if(existUsuario(idUsuario)){
            this.usuarioRepository.deleteById(idUsuario);
            result = true;
        }
        return result;
    }
}
