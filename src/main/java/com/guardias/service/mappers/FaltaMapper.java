package com.guardias.service.mappers;

import com.guardias.persintence.entity.Falta;
import com.guardias.persintence.entity.Tramo;
import com.guardias.service.dto.FaltaDTO;
import com.guardias.service.dto.TramoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FaltaMapper {
    @Autowired
    private TramoMapper tramoMapper;

    public FaltaDTO toDTO(Falta falta){
        FaltaDTO dto = new FaltaDTO();

        dto.setId(falta.getId());
        dto.setFecha(falta.getFecha());
        dto.setIdUsuario(falta.getIdUsuario());
        dto.setTarea(falta.getTarea());

        dto.setUsuario(falta.getUsuario().getNombreUsuario());

        List<TramoDTO> tramos = new ArrayList<TramoDTO>();
        for(Tramo t: falta.getTramos()){
            tramos.add(tramoMapper.toDTO(t));
        }

        return dto;
    }
}
