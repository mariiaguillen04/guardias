package com.guardias.service.dto;

import com.guardias.persintence.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RolDTO {

    private int id;
    private String nombreRol;
    private List<Usuario> usuario;
}
