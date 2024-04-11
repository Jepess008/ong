package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.AyudaMaterialDTO;
import com.campuslands.ong.repositories.entities.AyudaMateriialEntity;

public interface AyudaMaterialService {
    
    List<AyudaMaterialDTO>findAll();

    AyudaMaterialDTO findById(Long id);

    AyudaMaterialDTO save(AyudaMaterialDTO ayudaMaterial);

    AyudaMateriialEntity update(Long id, AyudaMateriialEntity ayudaMaterial);

    void delete(Long id);
}
