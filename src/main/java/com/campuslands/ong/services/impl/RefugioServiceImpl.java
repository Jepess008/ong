package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.config.RefugioDTOConverter;
import com.campuslands.ong.dto.RefugioDTO;
import com.campuslands.ong.repositories.RepositoryCiudad;
import com.campuslands.ong.repositories.RepositoryRefugio;
import com.campuslands.ong.repositories.entities.CiudadEntity;
import com.campuslands.ong.repositories.entities.RefugioEntity;
import com.campuslands.ong.services.RefugioService;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RefugioServiceImpl implements RefugioService {


    private RepositoryRefugio repositoryRefugio;
    private RefugioDTOConverter converter;
    private RepositoryCiudad repositoryCiudad;

    @Override
    @Transactional(readOnly = true)
    public List<RefugioDTO> findAll() {
        List<RefugioEntity> refugios= (List<RefugioEntity>)repositoryRefugio.findAll();
        return refugios.stream()
                        .map(refugio-> converter.convertRefugioDTO(refugio))
                        .toList();
    }   

    @Override
    @Transactional(readOnly = true)
    public RefugioDTO findById(Long id) {
        RefugioEntity refugio = repositoryRefugio.findById(id).orElse(null);
        return converter.convertRefugioDTO(refugio);
    }

    
    @Override
    @Transactional
    public RefugioDTO save(RefugioDTO refugio) {
        Optional<CiudadEntity> ciudadOptional= repositoryCiudad.findById(refugio.getId_ciudad());

        if(ciudadOptional.isPresent()){
            RefugioEntity refugioEntity= converter.convertRefugio(refugio);
            refugioEntity.setCiudad(ciudadOptional.get());
            repositoryRefugio.save(refugioEntity);
            return converter.convertRefugioDTO(refugioEntity);
        }

        return null;
    }


    @Override
    @Transactional
    public RefugioEntity update(Long id, RefugioEntity refugio) {
        Optional<RefugioEntity> refugioCurrentOptional=repositoryRefugio.findById(id);

        if(refugioCurrentOptional.isPresent()){
            RefugioEntity refugioCurrent=refugioCurrentOptional.get();
            refugioCurrent.setNombre(refugio.getNombre());

            repositoryRefugio.save(refugioCurrent);
            return refugioCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<RefugioEntity> refugioiOptional=repositoryRefugio.findById(id);
        if(refugioiOptional.isPresent()){
             repositoryRefugio.delete(refugioiOptional.get());
        }
    }
    
}
