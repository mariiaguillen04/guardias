package com.guardias.service.mappers;

import com.guardias.persintence.entity.Tramo;
import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.repository.UsuarioRepository;
import com.guardias.service.dto.RolDTO;
import com.guardias.service.dto.TramoDTO;
import com.guardias.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TramoMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TramoDTO toDTO(Tramo tramo){

        TramoDTO dto = new TramoDTO();

        dto.setId(tramo.getId());
        dto.setHora(tramo.getHora());
        dto.setFecha(tramo.getFecha());
        dto.setCurso(tramo.getCurso());
        dto.setAula(tramo.getAula());
        if (tramo.getUsuarios() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(tramo.getUsuarios().getId());
            usuarioDTO.setNombreUsuario(tramo.getUsuarios().getNombreUsuario());
            usuarioDTO.setEmail(tramo.getUsuarios().getEmail());
            List<RolDTO> rolDTOs = tramo.getUsuarios().getRoles().stream().map(rol -> {
                RolDTO rdto = new RolDTO();
                rdto.setId(rol.getId());
                rdto.setNombreRol(rol.getNombreRol().toString());
                return rdto;
            }).collect(Collectors.toList());
            usuarioDTO.setRoles(rolDTOs);
            dto.setUsuarios(usuarioDTO);
        }

        return  dto;
    }


    public Tramo toEntity(TramoDTO dto){
         Tramo tramo = new Tramo();

         tramo.setId(dto.getId());
         tramo.setHora(dto.getHora());
         tramo.setFecha(dto.getFecha());
         tramo.setCurso(dto.getCurso());
         tramo.setAula(dto.getAula());
        if (dto.getUsuarios() != null && dto.getUsuarios().getId() != 0) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarios().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            tramo.setUsuarios(usuario);
        }

         return tramo;
    }
}
