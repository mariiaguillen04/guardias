package com.guardias.service.mappers;

import com.guardias.persintence.entity.Rol;
import com.guardias.persintence.entity.enums.Roles;
import com.guardias.service.dto.RolDTO;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolDTO toDTO(Rol rol){
        RolDTO dto = new RolDTO();

        dto.setId(rol.getId());
        dto.setNombreRol(Roles.DIRECTORA.toString());
        dto.setNombreRol(Roles.JEFAESTUDIOS.toString());
        dto.setNombreRol(Roles.PROFESOR.toString());
        dto.setNombreRol(Roles.SUBDIRECTORA.toString());

        return dto;
    }
}
