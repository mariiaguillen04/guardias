package com.guardias.service;

import com.guardias.persintence.entity.Anexo;
import com.guardias.persintence.repository.AnexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AnexoService {
    @Autowired
    private AnexoRepository anexoRepository;

    //Existe Anexo
    public boolean existsAnexo(int idAnexo){return this.anexoRepository.existsById(idAnexo);}

    //Encontrar todos los anexos
    public List<Anexo> findAll(){return this.anexoRepository.findAll();}

    //Encontrar Anexo por Id
    public Optional<Anexo> findById(int idAnexo){
        Optional<Anexo> anexo = this.anexoRepository.findById(idAnexo);
        if(existsAnexo(idAnexo)){
            return anexo;
        }else{
            throw new IllegalArgumentException("El anexo con id"+idAnexo+"no está registrado");
        }
    }

    //Crear un Anexo
    public Anexo create(Anexo anexo){
        return this.anexoRepository.save(anexo);
    }

    //Actuallizar Anexo
    public Anexo update(Anexo anexo, int idAnexo){
        if(existsAnexo(idAnexo)){
            return this.anexoRepository.save(anexo);
        }else{
            throw new IllegalArgumentException("El anexo con id"+idAnexo+"no está registrado");
        }

    }

    //Eliminar Anexo
    public boolean delete(int idAnexo){
        boolean result = false;
        if(existsAnexo(idAnexo)){
            this.anexoRepository.deleteById(idAnexo);
            result = true;
        }else{
            throw new IllegalArgumentException("El anexo con id"+idAnexo+"no está registrado");
        }
        return result;
    }
}
