package com.campuslands.ong.repositories;


import org.springframework.data.repository.CrudRepository;

import com.campuslands.ong.repositories.entities.SocioEntity;

public interface RepositorySocio extends CrudRepository<SocioEntity,Long> {
    

}
