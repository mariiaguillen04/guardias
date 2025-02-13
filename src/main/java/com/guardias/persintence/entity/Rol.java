package com.guardias.persintence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guardias.persintence.entity.enums.Roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Roles rol;

	@Column(name = "id_usuario")
	private int idUsuario;

	@OneToMany(mappedBy = "rol", orphanRemoval = true)
	@JsonIgnore
	private Usuario usuario;

}
