package com.guardias.persintence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tarea")
@Getter
@Setter
@NoArgsConstructor
public class Falta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="id_usuario",nullable = false,unique = true)
    private int idUsuario;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private LocalDate fecha;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String tarea;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "falta", orphanRemoval = true)
    private List<Tramo> tramos;

}
