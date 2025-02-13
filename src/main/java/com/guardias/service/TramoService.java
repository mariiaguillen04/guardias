package com.guardias.service;

import com.guardias.persintence.entity.Anexo;
import com.guardias.persintence.entity.Tramo;
import com.guardias.persintence.repository.TramoRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TramoService {

    @Autowired
    private TramoRepository tramoRepository;

    //Encontrar todos los Tramos
    public List<Tramo> findAll(){
        return this.tramoRepository.findAll();
    }

    //Existe un tramo
    public boolean existsTramo(int idTramo){
        return this.tramoRepository.existsById(idTramo);
    }

    //Encontrar Tramo por id
    public Optional<Tramo> findById(int idTramo){
        return this.tramoRepository.findById(idTramo);
    }

    //Crear un tramo
    public Tramo create(Tramo tramo){
        return this.tramoRepository.save(tramo);
    }

    //Actualizar un tramo
    public Tramo update(Tramo tramo, int idTramo){
        if(existsTramo(idTramo)){
            return this.tramoRepository.save(tramo);
        }else{
            throw new IllegalArgumentException("El tramo con id"+idTramo+"no está registrado");
        }

    }

    //Eliminar un tramo determinado
    public boolean delete(int idTramo){
        boolean result = false;
        if(existsTramo(idTramo)){
            this.tramoRepository.deleteById(idTramo);
            result = true;
        }
        return result;
    }
}
