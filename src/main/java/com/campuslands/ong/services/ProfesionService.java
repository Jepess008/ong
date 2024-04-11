package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.repositories.entities.ProfesionEntity;

public interface ProfesionService {
    

    List<ProfesionEntity>findAll();
    
    ProfesionEntity findById(Long id);

    ProfesionEntity save(ProfesionEntity profesion);

    ProfesionEntity update(Long id, ProfesionEntity profesion);

    void delete(Long id);
}
