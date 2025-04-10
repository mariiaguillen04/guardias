package com.guardias.service.dto;

import com.guardias.persintence.entity.Falta;
import com.guardias.persintence.entity.Rol;
import com.guardias.persintence.entity.Tramo;
import com.guardias.persintence.entity.enums.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    private int id;
    private String nombreUsuario;
    private String email;
    private List<RolDTO> roles;

}
