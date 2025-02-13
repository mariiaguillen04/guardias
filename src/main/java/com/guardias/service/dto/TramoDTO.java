package com.guardias.service.dto;

import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.entity.enums.Hora;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TramoDTO {

    private int id;
    private Hora hora;
    private LocalDate fecha;
    private String curso;
    private String aula;
    private Usuario usuarios;
}
