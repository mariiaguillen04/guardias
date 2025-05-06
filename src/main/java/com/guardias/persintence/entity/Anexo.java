package com.guardias.persintence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="anexo")
@Getter
@Setter
@NoArgsConstructor
public class Anexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(200)")
    private String comentario;

    @Column(name="fecha_ausencia", columnDefinition = "DATE", nullable = false)
    private LocalDate fechaAusencia;

    @Column(columnDefinition = "VARCHAR(2050)")
    private String justificante;

    @ManyToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;
}
