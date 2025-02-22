package com.guardias.service;

import com.guardias.persintence.entity.Tramo;
import com.guardias.persintence.repository.TramoRepository;
import com.guardias.service.dto.TramoDTO;
import com.guardias.service.mappers.TramoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TramoService {

    @Autowired
    private TramoRepository tramoRepository;

    @Autowired
    private TramoMapper tramoMapper;

    // Encontrar todos los tramos
    public List<TramoDTO> findAll() {
        return this.tramoRepository.findAll().stream()
                .map(tramoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Verificar si un tramo existe por su id
    public boolean existsTramo(int idTramo) {
        return this.tramoRepository.existsById(idTramo);
    }

    // Encontrar un tramo por id
    public Tramo findById(int idTramo) {
        return this.tramoRepository.findById(idTramo)
                .orElseThrow(() -> new EntityNotFoundException("Tramo con id " + idTramo + " no encontrado"));
    }

    // Crear un tramo
    public Tramo create(Tramo tramo) {
        // Verificar si el usuario relacionado con el tramo existe (por ejemplo, el id_usuario asociado)
        if (tramo.getUsuarios() == null || tramo.getUsuarios().getId() == 0) {
            throw new IllegalArgumentException("El tramo debe tener un usuario asignado");
        }
        return this.tramoRepository.save(tramo);
    }

    // Actualizar un tramo
    public Tramo update(Tramo tramo, int idTramo) {
        // Verificar si el tramo existe antes de actualizarlo
        if (!existsTramo(idTramo)) {
            throw new EntityNotFoundException("El tramo con id " + idTramo + " no está registrado");
        }

        // Establecer el id del tramo si no lo tiene asignado
        tramo.setId(idTramo);
        return this.tramoRepository.save(tramo);
    }

    // Eliminar un tramo
    public boolean delete(int idTramo) {
        boolean result = false;
        if (existsTramo(idTramo)) {
            this.tramoRepository.deleteById(idTramo);
            result = true;
        }
        return result;
    }
}
