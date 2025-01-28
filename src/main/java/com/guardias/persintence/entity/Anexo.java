package com.guardias.persintence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="anexo")
@Getter
@Setter
@NoArgsConstructor

public class Anexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="id_usuario", nullable = false, unique = true)
    private int idUsuario;

    @Column(columnDefinition = "VARCHAR(200)")
    private String comentario;

    @Column(name="fecha_ausencia", columnDefinition = "DATE", nullable = false)
    private LocalDate fechaAusencia;

    @Column(columnDefinition = "VARCHAR(2050")
    private String justificante;

    @OneToMany(mappedBy = "anexo")
    @JsonIgnore
    private List<Usuario> usuarios;
}
