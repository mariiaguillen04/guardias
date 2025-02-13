package com.guardias.service;

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

    public List<Tramo> findAll(){
        return this.tramoRepository.findAll();
    }

    public boolean existsTramo(int idTramo){
        return this.tramoRepository.existsById(idTramo);
    }

    public Optional<Tramo> findById(int idTramo){
        return this.tramoRepository.findById(idTramo);
    }

    public Tramo create(Tramo tramo){
        return this.tramoRepository.save(tramo);
    }

    public Tramo update(Tramo tramo){
        return this.tramoRepository.save(tramo);
    }

    public boolean delete(int idTramo){
        boolean result = false;
        if(existsTramo(idTramo)){
            this.tramoRepository.deleteById(idTramo);
            result = true;
        }
        return result;
    }
}
