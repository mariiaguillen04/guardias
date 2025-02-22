package com.guardias.service.mappers;

import com.guardias.persintence.entity.Falta;
import com.guardias.persintence.entity.Tramo;
import com.guardias.persintence.entity.Usuario;
import com.guardias.service.dto.FaltaDTO;
import com.guardias.service.dto.TramoDTO;
import com.guardias.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    @Autowired
    private TramoMapper tramoMapper;

    @Autowired
    private FaltaMapper faltaMapper;

    public UsuarioDTO toDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRoles());

        if (usuario.getTramo() != null) {
            dto.setTramo(usuario.getTramo()); // Mapea la lista de tramos
        }

        List<TramoDTO> tramos = new ArrayList<TramoDTO>();
        for(Tramo t: usuario.getTramo()){
            tramos.add(tramoMapper.toDTO(t));
        }

        List<FaltaDTO> faltas = new ArrayList<FaltaDTO>();
        for(Falta f: usuario.getFalta()){
            faltas.add(faltaMapper.toDTO(f));
        }
        return dto;
    }


    public Usuario toEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRoles(usuarioDTO.getRol());
        usuario.setTramo(usuarioDTO.getTramo());
        usuario.setTramo(usuarioDTO.getTramo());
        usuario.setFalta(usuarioDTO.getFalta());

        return usuario;
    }
}
