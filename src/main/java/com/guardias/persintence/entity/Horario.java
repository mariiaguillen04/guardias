package com.guardias.persintence.entity;

import com.guardias.persintence.entity.enums.Dia;
import com.guardias.persintence.entity.enums.Hora;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="horario")
@Getter
@Setter
@NoArgsConstructor
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="id_usuario", nullable = false, unique = true)
    private int idUsuario;

    @Column(columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private Dia dia;

    @Column(columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private Hora hora;
}
