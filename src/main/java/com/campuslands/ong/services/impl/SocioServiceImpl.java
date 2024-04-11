package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.config.SocioDTOConverter;
import com.campuslands.ong.dto.SocioDTO;
import com.campuslands.ong.repositories.RepositoryPersona;
import com.campuslands.ong.repositories.RepositorySede;
import com.campuslands.ong.repositories.RepositorySocio;
import com.campuslands.ong.repositories.RepositoryTipo_Cuota;
import com.campuslands.ong.repositories.entities.PersonaEntity;
import com.campuslands.ong.repositories.entities.SedeEntity;
import com.campuslands.ong.repositories.entities.SocioEntity;
import com.campuslands.ong.repositories.entities.Tipo_cuotaEntity;
import com.campuslands.ong.services.SocioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SocioServiceImpl implements SocioService {

    private RepositorySocio repositorySocio;
    private SocioDTOConverter converter;
    private RepositorySede repositorySede;
    private RepositoryPersona repositoryPersona;
    private RepositoryTipo_Cuota repositoryTipo_Cuota;

    @Override
    @Transactional(readOnly = true)
    public List<SocioDTO> findAllS() {
        List<SocioEntity> socios =(List<SocioEntity>) repositorySocio.findAll();
        return socios.stream()
                    .map(socio-> converter.convertSocioDTO(socio))
                    .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SocioDTO findById(Long id) {
        SocioEntity socio = repositorySocio.findById(id).orElse(null);
        return converter.convertSocioDTO(socio);
    }


    @Override
    @Transactional
    public SocioDTO save(SocioDTO socio) {
        Optional<PersonaEntity> persOptional=repositoryPersona.findById(socio.getId_persona());
        Optional<SedeEntity> sedeOptional= repositorySede.findById(socio.getId_sede());
        Optional<Tipo_cuotaEntity> tipo_cuotaOptional= repositoryTipo_Cuota.findById(socio.getId_tipo_cuota());

        if(persOptional.isPresent() && sedeOptional.isPresent() && tipo_cuotaOptional.isPresent()){
            SocioEntity socioEntity= converter.convertSocio(socio);
            socioEntity.setPersona(persOptional.get());
            socioEntity.setSede(sedeOptional.get());
            socioEntity.setTipo_cuota(tipo_cuotaOptional.get());
            repositorySocio.save(socioEntity);
            return converter.convertSocioDTO(socioEntity);
        }

        return null;
    }

    @Override
    @Transactional
    public SocioEntity update(Long id, SocioEntity socio) {
       Optional<SocioEntity> socioCurrentOptional=repositorySocio.findById(id);
        
        if(socioCurrentOptional.isPresent()){
            SocioEntity socioCurrent=socioCurrentOptional.get();
            socioCurrent.setTipo_cuota(socio.getTipo_cuota());
            repositorySocio.save(socioCurrent);
            return socioCurrent;
        }
        return null;

    }

    @Override
    public void delete(Long id) {
        Optional<SocioEntity> socOptional=repositorySocio.findById(id);
       if(socOptional.isPresent()){
            repositorySocio.delete(socOptional.get());
       }
    }

   
    
    
}
