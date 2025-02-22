package com.guardias.service;


import com.guardias.persintence.entity.Falta;
import com.guardias.persintence.repository.FaltaRepository;
import com.guardias.service.dto.FaltaDTO;
import com.guardias.service.mappers.FaltaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FaltaService {
    @Autowired
    private FaltaRepository faltaRepository;

    @Autowired
    private FaltaMapper faltaMapper;

    //Existe Falta
    public boolean existsFalta(int idFalta){return this.faltaRepository.existsById(idFalta);}

    //Encontrar todos los anexos
    public List<FaltaDTO> findAll(){return this.faltaRepository.findAll().stream().map(faltaMapper::toDTO).collect(Collectors.toList());}

    //Encontrar Falta por Id
    public Optional<Falta> findById(int idFalta){
        Optional<Falta> anexo = this.faltaRepository.findById(idFalta);
        if(existsFalta(idFalta)){
            return anexo;
        }else{
            throw new IllegalArgumentException("La falta con id"+idFalta+"no está registrado");
        }
    }

    //Crear un Falta
    public Falta create(Falta falta){
        return this.faltaRepository.save(falta);
    }

    //Actuallizar Falta
    public Falta update(Falta falta, int idFalta){
        if(existsFalta(idFalta)){
            return this.faltaRepository.save(falta);
        }else{
            throw new IllegalArgumentException("La falta con id"+idFalta+"no está registrado");
        }

    }

    //Eliminar Falta
    public boolean delete(int idFalta){
        boolean result = false;
        if(existsFalta(idFalta)){
            this.faltaRepository.deleteById(idFalta);
            result = true;
        }else{
            throw new IllegalArgumentException("La falta con id"+idFalta+"no está registrado");
        }
        return result;
    }
}
