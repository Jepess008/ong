package com.campuslands.ong.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campuslands.ong.repositories.entities.VoluntarioEntity;

public interface RepositoryVoluntario extends CrudRepository<VoluntarioEntity,Long> {
    
}
