package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.repositories.entities.PersonaEntity;

public interface PersonaService {
    
    List<PersonaEntity>findAll();

    PersonaEntity findById(Long id);

    PersonaEntity save(PersonaEntity persona);

    PersonaEntity update(Long id, PersonaEntity persona);

    void delete(Long id);
}
