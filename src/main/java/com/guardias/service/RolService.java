package com.guardias.service;

import com.guardias.persintence.entity.Rol;
import com.guardias.persintence.entity.Tramo;
import com.guardias.persintence.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    //Encontrar todos los Roles
    public List<Rol> findAll(){
        return this.rolRepository.findAll();
    }

    //Existe un Rol determinado
    public boolean existsRol(int idRol){
        return this.rolRepository.existsById(idRol);
    }

    //Encontrar un rol por id
    public Optional<Rol> findById(int idRol){
        return this.rolRepository.findById(idRol);
    }

    //Crear Rol
    public Rol create(Rol rol){
        return this.rolRepository.save(rol);
    }

    //Modificar un Rol
    public Rol update(Rol rol, int idRol){
        if(existsRol(idRol)){
            return this.rolRepository.save(rol);
        }else{
            throw new IllegalArgumentException("El rol con id"+idRol+"no está registrado");
        }

    }
    //Eliminar un Rol
    public boolean delete(int idRol){
        boolean result = false;
        if(existsRol(idRol)){
            this.rolRepository.deleteById(idRol);
            result = true;
        }
        return result;
    }
}
