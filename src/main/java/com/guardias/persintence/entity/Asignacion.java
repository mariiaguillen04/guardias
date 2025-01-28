package com.guardias.persintence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "asignacion")
@Getter
@Setter
@NoArgsConstructor
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="id_usuario", nullable = false, unique = true)
    private int idUsuario;

    @Column(name = "id_tramo", nullable = false, unique = true)
    private int idTramo;

    @Column(name = "id_tarea", nullable = false, unique = true)
    private int idTarea;

}
