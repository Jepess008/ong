package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.config.EnvioDTOConverter;
import com.campuslands.ong.dto.EnvioDTO;
import com.campuslands.ong.repositories.RepositoryEnvio;
import com.campuslands.ong.repositories.RepositoryRefugio;
import com.campuslands.ong.repositories.entities.EnvioEntity;
import com.campuslands.ong.repositories.entities.RefugioEntity;
import com.campuslands.ong.services.EnvioService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EnvioServiceImpl implements EnvioService {

    private RepositoryEnvio repositoryEnvio;
    private EnvioDTOConverter converter;
    private RepositoryRefugio repositoryRefugio;

    
    @Override
    @Transactional(readOnly = true)    
    public List<EnvioDTO> findAll() {
        List<EnvioEntity> envios=(List<EnvioEntity>) repositoryEnvio.findAll();
        return envios.stream()
                    .map(envio-> converter.convertEnvioDTO(envio))
                    .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EnvioDTO findById(Long id) {
        EnvioEntity envio= repositoryEnvio.findById(id).orElse(null);
        return converter.convertEnvioDTO(envio);
    }

    @Override
    @Transactional
    public EnvioDTO save(EnvioDTO envio) {
        Optional<RefugioEntity> refugioOptional= repositoryRefugio.findById(envio.getId_refugio());

        if(refugioOptional.isPresent()){
            EnvioEntity enveioEntity= converter.convertEnvio(envio);
            enveioEntity.setRefugio(refugioOptional.get());
            return converter.convertEnvioDTO(enveioEntity);
        }
        return null;
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Optional<EnvioEntity> envOptional=repositoryEnvio.findById(id);
       if(envOptional.isPresent()){
            repositoryEnvio.delete(envOptional.get());
       }
    }
    
}
