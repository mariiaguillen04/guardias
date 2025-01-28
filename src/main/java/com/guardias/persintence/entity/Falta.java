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
    
    @OneToMany(mappedBy = "usuario")
    private List<Usuario> usuario;

}
