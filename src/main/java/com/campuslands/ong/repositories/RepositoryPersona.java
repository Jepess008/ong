package com.campuslands.ong.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campuslands.ong.repositories.entities.PersonaEntity;

public interface RepositoryPersona extends CrudRepository<PersonaEntity,Long> {
    
    /* Debo ingresar un metodo para buscar por DNI */
}
