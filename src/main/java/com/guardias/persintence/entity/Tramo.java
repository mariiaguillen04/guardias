package com.guardias.persintence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.guardias.persintence.entity.enums.Hora;

@Entity
@Table(name="tramo")
@Getter
@Setter
@NoArgsConstructor
public class Tramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="id_usuario",nullable = false,unique = true)
    private int idUsuario;

    @Column(columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private Hora hora;
    
    @Column(name="fecha_entrada")
    private LocalDate fecha;

    @Column(columnDefinition = "VARCHAR(20)")
    private String curso;

    @Column(columnDefinition = "VARCHAR(50)")
    private String aula;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuarios;

}
