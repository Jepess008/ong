package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.config.SedeDTOConverter;
import com.campuslands.ong.dto.SedeDTO;
import com.campuslands.ong.repositories.RepositoryCiudad;
import com.campuslands.ong.repositories.RepositoryPersona;
import com.campuslands.ong.repositories.RepositorySede;
import com.campuslands.ong.repositories.entities.CiudadEntity;
import com.campuslands.ong.repositories.entities.PersonaEntity;
import com.campuslands.ong.repositories.entities.SedeEntity;
import com.campuslands.ong.services.SedeService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SedeServiceImpl implements SedeService{

    private RepositoryCiudad repositoryCiudad;
    private RepositorySede repositorySede;
    private RepositoryPersona repositoryPersona;
    private SedeDTOConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<SedeDTO> findAllSe() {
        List<SedeEntity> sedes =(List<SedeEntity>) repositorySede.findAll();
        return sedes.stream()
                    .map(sede -> converter.convertSedeDTO(sede))
                    .toList();


    }

    @Override
    @Transactional(readOnly = true)
    public SedeDTO findById(Long id) {
      SedeEntity sede =  repositorySede.findById(id).orElse(null);
      return converter.convertSedeDTO(sede);
    }

    @Override
    @Transactional
    public SedeDTO save(SedeDTO sede) {

        Optional<CiudadEntity> ciudadOptional= repositoryCiudad.findById(sede.getId_ciudad());
        Optional<PersonaEntity> personaOptional= repositoryPersona.findById(sede.getId_director());

        if(ciudadOptional.isPresent() && personaOptional.isPresent()){
            SedeEntity sedeEntity= converter.convertSede(sede);
            sedeEntity.setCiudad(ciudadOptional.get());
            sedeEntity.setDirector(personaOptional.get());
            repositorySede.save(sedeEntity);
            return converter.convertSedeDTO(sedeEntity);
        }
        
        return null;
    }

    @Override
    @Transactional
    public SedeEntity update(Long id, SedeEntity sede) {
       Optional<SedeEntity> sedeCurrentOptional=repositorySede.findById(id);

        if(sedeCurrentOptional.isPresent()){
            SedeEntity sedeCurrent=sedeCurrentOptional.get();
            sedeCurrent.setDireccion(sede.getDireccion());
            repositorySede.save(sedeCurrent);
            return sedeCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<SedeEntity> sedeOptional=repositorySede.findById(id);
        if(sedeOptional.isPresent()){
             repositorySede.delete(sedeOptional.get());
        }
    }
    
}
