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

    @Column(columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String nombre_usuario;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String password;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JsonIgnore
    private List<Roles> rol;
    
    @ManyToOne
    @JoinColumn(name="id_anexo", referencedColumnName = "id", insertable = false, updatable = false)
    private Anexo anexo;
    
   
    @OneToMany(mappedBy = "id_usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tramo> tramo;
    
    @OneToMany(mappedBy = "id_usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Falta> falta;
    

}
