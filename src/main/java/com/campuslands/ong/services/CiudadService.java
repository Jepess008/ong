package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.repositories.entities.CiudadEntity;

public interface CiudadService {
    
    List<CiudadEntity>findAll();

    CiudadEntity findById(Long id);

    CiudadEntity save(CiudadEntity ciudad);

    CiudadEntity update(Long id, CiudadEntity ciudad);

    void delete(Long id);
}
