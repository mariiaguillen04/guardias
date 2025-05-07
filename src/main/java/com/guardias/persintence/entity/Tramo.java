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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(100)")
    private Hora hora;

    @Column(name="fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(columnDefinition = "VARCHAR(20)")
    private String curso;

    @Column(columnDefinition = "VARCHAR(50)")
    private String aula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_falta", referencedColumnName = "id")
    private Falta falta;
}
