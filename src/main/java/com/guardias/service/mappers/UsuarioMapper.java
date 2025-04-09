package com.guardias.service.mappers;

import com.guardias.persintence.entity.Falta;
import com.guardias.persintence.entity.Rol;
import com.guardias.persintence.entity.Tramo;
import com.guardias.persintence.entity.Usuario;
import com.guardias.service.dto.FaltaDTO;
import com.guardias.service.dto.RolDTO;
import com.guardias.service.dto.TramoDTO;
import com.guardias.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    @Autowired
    private TramoMapper tramoMapper;

    @Autowired
    private FaltaMapper faltaMapper;

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setEmail(usuario.getEmail());

        // Convertir roles a DTO
        if (usuario.getRoles() != null) {
            List<RolDTO> roles = usuario.getRoles().stream().map(rol -> {
                RolDTO rolDTO = new RolDTO();
                rolDTO.setId(rol.getId());
                rolDTO.setNombreRol(rol.getNombreRol().toString());
                return rolDTO;
            }).collect(Collectors.toList());
            dto.setRoles(roles);
        }

        // Convertir tramos a DTO
        if (usuario.getTramo() != null) {
            List<TramoDTO> tramos = usuario.getTramo().stream()
                    .map(tramoMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setTramos(tramos);
        }

        // Convertir faltas a DTO
        if (usuario.getFalta() != null) {
            List<FaltaDTO> faltas = usuario.getFalta().stream()
                    .map(faltaMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setFaltas(faltas);
        }

        return dto;
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setEmail(usuarioDTO.getEmail());

        return usuario;
    }
}
