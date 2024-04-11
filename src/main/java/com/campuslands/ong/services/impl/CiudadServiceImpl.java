package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.repositories.RepositoryCiudad;
import com.campuslands.ong.repositories.entities.CiudadEntity;
import com.campuslands.ong.services.CiudadService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CiudadServiceImpl implements CiudadService{


    private RepositoryCiudad repositoryCiudad;



    @Override
    @Transactional(readOnly = true)
    public List<CiudadEntity> findAll() {
        return (List<CiudadEntity>) repositoryCiudad.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CiudadEntity findById(Long id) {
        return repositoryCiudad.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public CiudadEntity save(CiudadEntity ciudad) {

       return repositoryCiudad.save(ciudad);
    }

    @Override
    @Transactional
    public CiudadEntity update(Long id, CiudadEntity ciudad) {
        Optional<CiudadEntity> ciudadCurrentOptional=repositoryCiudad.findById(id);

        if(ciudadCurrentOptional.isPresent()){
            CiudadEntity ciudadCurrent=ciudadCurrentOptional.get();
            ciudadCurrent.setNombre(ciudad.getNombre());
            repositoryCiudad.save(ciudadCurrent);
            return ciudadCurrent;
        }

        return null;

    }   

    @Override
    public void delete(Long id) {
       Optional<CiudadEntity> ciudadOptional=repositoryCiudad.findById(id);
       if(ciudadOptional.isPresent()){
            repositoryCiudad.delete(ciudadOptional.get());
       }
    }
    
}
