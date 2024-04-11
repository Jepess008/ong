package com.campuslands.ong.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.campuslands.ong.repositories.entities.UserEntity;

public interface RepositoryUser extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    
}
