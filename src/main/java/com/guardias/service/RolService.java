package com.guardias.service;

import com.guardias.persintence.entity.Rol;
import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.repository.RolRepository;
import com.guardias.persintence.repository.UsuarioRepository;
import com.guardias.service.dto.RolDTO;
import com.guardias.service.mappers.RolMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolMapper rolMapper;

    // Encontrar todos los roles
    public List<RolDTO> findAll() {
        return this.rolRepository.findAll().stream().map(rolMapper::toDTO).collect(Collectors.toList());
    }

    // Verificar si un rol existe por su id
    public boolean existsRol(int idRol) {
        return this.rolRepository.existsById(idRol);
    }

    // Encontrar un rol por su id
    public Rol findById(int idRol) {
        return this.rolRepository.findById(idRol)
                .orElseThrow(() -> new EntityNotFoundException("Rol con id " + idRol + " no encontrado"));
    }

    // Crear un rol
    public Rol create(Rol rol) {
        // Verificar que el usuario asignado al rol exista
        if (rol.getUsuario() == null || !usuarioRepository.existsById(rol.getUsuario().getId())) {
            throw new IllegalArgumentException("El usuario asociado al rol no existe");
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(rol.getUsuario().getId());
        if (usuarioOpt.isPresent()) {
            // Asignar el usuario cargado al rol
            rol.setUsuario(usuarioOpt.get());
        } else {
            throw new IllegalArgumentException("El usuario con el id proporcionado no fue encontrado.");
        }

        return this.rolRepository.save(rol);
    }

    // Modificar un rol
    public Rol update(Rol rol, int idRol) {
        if (!existsRol(idRol)) {
            throw new EntityNotFoundException("El rol con id " + idRol + " no está registrado");
        }

        // Verificar que el usuario asociado exista
        if (rol.getUsuario() == null || !usuarioRepository.existsById(rol.getUsuario().getId())) {
            throw new IllegalArgumentException("El usuario asociado al rol no existe");
        }

        // Establecer el ID para que sea consistente en la base de datos
        rol.setId(idRol);
        return this.rolRepository.save(rol);
    }

    // Eliminar un rol
    public boolean delete(int idRol) {
        boolean result = false;
        if (existsRol(idRol)) {
            this.rolRepository.deleteById(idRol);
            result = true;
        }
        return result;
    }
}
