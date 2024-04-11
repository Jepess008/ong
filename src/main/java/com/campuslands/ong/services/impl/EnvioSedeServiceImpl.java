package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.config.EnvioSedeDTOConverter;
import com.campuslands.ong.dto.EnvioSedeDTO;
import com.campuslands.ong.repositories.RepositoryEnvio;
import com.campuslands.ong.repositories.RepositoryEnvioSede;
import com.campuslands.ong.repositories.RepositorySede;
import com.campuslands.ong.repositories.entities.EnvioEntity;
import com.campuslands.ong.repositories.entities.EnvioSedeEntity;
import com.campuslands.ong.repositories.entities.SedeEntity;
import com.campuslands.ong.services.EnvioSedeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnvioSedeServiceImpl implements EnvioSedeService {

   private RepositoryEnvioSede repositoryEnvioSede;
   private EnvioSedeDTOConverter converter;
   private RepositoryEnvio repositoryEnvio;
   private RepositorySede repositorySede;

   
    @Override
    @Transactional(readOnly = true)
    public List<EnvioSedeDTO> findAll() {
        List<EnvioSedeEntity> enviossede=(List<EnvioSedeEntity>)repositoryEnvioSede.findAll();
        return enviossede.stream()
                        .map(enviosede -> converter.convertEnvioSedeDTO(enviosede))
                        .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public EnvioSedeDTO findById(Long id) {
        EnvioSedeEntity enviosede= repositoryEnvioSede.findById(id).orElse(null);
        return converter.convertEnvioSedeDTO(enviosede);

    }

    @Transactional
    @Override
    public EnvioSedeDTO save(EnvioSedeDTO envio_x_sede) {
        
        Optional<SedeEntity> sedeOptional= repositorySede.findById(envio_x_sede.getId_sede());
        Optional<EnvioEntity> envioOptional= repositoryEnvio.findById(envio_x_sede.getId_envio());

        if(sedeOptional.isPresent() && envioOptional.isPresent()){
            EnvioSedeEntity envioSedeEntity= converter.convertEnvioSede(envio_x_sede);
            envioSedeEntity.setEnvio(envioOptional.get());
            envioSedeEntity.setSede(sedeOptional.get());
            return converter.convertEnvioSedeDTO(envioSedeEntity);
        }

        return null;
    }   


    @Override
    public void delete(Long id) {
        Optional<EnvioSedeEntity> enviosedeOptional=repositoryEnvioSede.findById(id);
       if(enviosedeOptional.isPresent()){
            repositoryEnvioSede.delete(enviosedeOptional.get());
       }
    }
    
}
