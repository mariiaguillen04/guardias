package com.guardias.persintence.repository;

import com.guardias.persintence.entity.Falta;
import org.springframework.data.repository.ListCrudRepository;

public interface TareaRepository extends ListCrudRepository<Falta,Integer> {

}
