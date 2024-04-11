package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.repositories.RepositoryTipo_Cuota;
import com.campuslands.ong.repositories.entities.Tipo_cuotaEntity;
import com.campuslands.ong.services.Tipo_CuotaService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class Tipo_CuotaServiceImpl implements Tipo_CuotaService {

    private RepositoryTipo_Cuota repositoryTipo_Cuota;


    @Override
    @Transactional(readOnly = true)
    public List<Tipo_cuotaEntity> findAll() {
        return (List<Tipo_cuotaEntity>) repositoryTipo_Cuota.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Tipo_cuotaEntity findById(Long id) {
        return repositoryTipo_Cuota.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Tipo_cuotaEntity save(Tipo_cuotaEntity tipo_cuota) {
        return repositoryTipo_Cuota.save(tipo_cuota);
    }

    @Override
    @Transactional
    public Tipo_cuotaEntity update(Long id, Tipo_cuotaEntity tipo_cuota) {
        Optional<Tipo_cuotaEntity> tipo_cuotaCurrentOptional=repositoryTipo_Cuota.findById(id);

        if(tipo_cuotaCurrentOptional.isPresent()){
            Tipo_cuotaEntity tipo_cuentaCurrent=tipo_cuotaCurrentOptional.get();
            tipo_cuentaCurrent.setNombre(tipo_cuota.getNombre());
            repositoryTipo_Cuota.save(tipo_cuentaCurrent);
            return tipo_cuentaCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Tipo_cuotaEntity> tipo_cuotaOptional=repositoryTipo_Cuota.findById(id);
       if(tipo_cuotaOptional.isPresent()){
            repositoryTipo_Cuota.delete(tipo_cuotaOptional.get());
       }
    }
    
}
