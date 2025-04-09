package com.guardias.service.mappers;

import com.guardias.persintence.entity.Rol;
import com.guardias.persintence.entity.enums.Roles;
import com.guardias.service.dto.RolDTO;
import com.guardias.service.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolDTO toDTO(Rol rol){
        RolDTO dto = new RolDTO();

        dto.setId(rol.getId());
        dto.setNombreRol(rol.getNombreRol().toString());


        if (rol.getUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(rol.getUsuario().getId());
            usuarioDTO.setNombreUsuario(rol.getUsuario().getNombreUsuario());
            usuarioDTO.setEmail(rol.getUsuario().getEmail());
            dto.setUsuario(usuarioDTO);
        }

        return dto;
    }
}
