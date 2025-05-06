package com.guardias.persintence.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guardias.persintence.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre_usuario", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String password;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String email;

    // Relación con el rol: un usuario puede tener varios roles
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rol> roles;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    private List<Anexo> anexos;

    // Relación con tramos: un usuario puede tener varios tramos
    @OneToMany(mappedBy = "usuarios", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tramo> tramo;

    // Relación con faltas: un usuario puede tener varias faltas
    @OneToMany(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Falta> falta;
}
