package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.repositories.entities.Tipo_cuotaEntity;

public interface Tipo_CuotaService {
    
    List<Tipo_cuotaEntity>findAll();

    Tipo_cuotaEntity findById(Long id);

    Tipo_cuotaEntity save(Tipo_cuotaEntity tipo_cuota);

    Tipo_cuotaEntity update(Long id, Tipo_cuotaEntity tipo_cuota);

    void delete(Long id);
}
