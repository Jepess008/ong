package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.RefugioDTO;
import com.campuslands.ong.repositories.entities.RefugioEntity;

public interface RefugioService {
    
    List<RefugioDTO>findAll();

    RefugioDTO findById(Long id);

    RefugioDTO save(RefugioDTO refugio);

    RefugioEntity update(Long id, RefugioEntity refugio);

    void delete(Long id);
}
