package com.guardias.service;

import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.repository.UsuarioRepository;
import com.guardias.service.dto.UsuarioDTO;
import com.guardias.service.mappers.UsuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Obtener todos los usuarios mapeados a DTO
    public List<UsuarioDTO> findAll() {
        return this.usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO) // Mapear correctamente el objeto usuario
                .collect(Collectors.toList());
    }

    // Comprobar si existe un usuario por id
    public boolean existUsuario(int idUsuario) {
        return this.usuarioRepository.existsById(idUsuario);
    }

    // Buscar un usuario por su id
    public Usuario findById(int idUsuario) {
        return this.usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con id " + idUsuario + " no encontrado"));
    }

    // Crear un nuevo usuario
    @Transactional
    public Usuario create(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente
    @Transactional
    public Usuario update(Usuario usuario) {
        // Verificar si el usuario existe antes de actualizar
        if (!existUsuario(usuario.getId())) {
            throw new EntityNotFoundException("Usuario con id " + usuario.getId() + " no encontrado para actualización");

        }
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public boolean delete(int idUsuario) {
        // Verificar si el usuario existe
        Usuario usuario = this.usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con id " + idUsuario + " no encontrado para eliminar"));

        // Eliminar el usuario
        this.usuarioRepository.delete(usuario);
        return true;
    }
}