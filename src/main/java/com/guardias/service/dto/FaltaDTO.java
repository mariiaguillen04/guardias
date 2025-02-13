package com.guardias.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FaltaDTO {

    private int id;
    private int idUsuario;
    private LocalDate fecha;
    private String tarea;
    private String usuario;
    private List<TramoDTO> tramos;
}
