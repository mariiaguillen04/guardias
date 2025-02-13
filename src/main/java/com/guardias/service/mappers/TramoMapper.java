package com.guardias.service.mappers;

import com.guardias.persintence.entity.Tramo;
import com.guardias.service.dto.TramoDTO;
import org.springframework.stereotype.Component;

@Component
public class TramoMapper {

    public TramoDTO toDTO(Tramo tramo){

        TramoDTO dto = new TramoDTO();

        dto.setId(tramo.getId());
        dto.setHora(tramo.getHora());
        dto.setFecha(tramo.getFecha());
        dto.setCurso(tramo.getCurso());
        dto.setAula(tramo.getAula());
        dto.setUsuarios(tramo.getUsuarios());

        return  dto;
    }


    public Tramo toEntity(TramoDTO dto){
         Tramo tramo = new Tramo();

         tramo.setId(dto.getId());
         tramo.setHora(dto.getHora());
         tramo.setFecha(dto.getFecha());
         tramo.setCurso(dto.getCurso());
         tramo.setAula(dto.getAula());
         tramo.setUsuarios(dto.getUsuarios());

         return tramo;
    }
}
