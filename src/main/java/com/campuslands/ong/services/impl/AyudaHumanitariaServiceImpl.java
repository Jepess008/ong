package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.config.AyudaHumanitariaDTOConverter;
import com.campuslands.ong.dto.AyudaHumanitariaDTO;
import com.campuslands.ong.repositories.RepositoryAyudaHumanitaria;
import com.campuslands.ong.repositories.RepositoryEnvioSede;
import com.campuslands.ong.repositories.entities.AyudaHumanitariaEntity;
import com.campuslands.ong.repositories.entities.EnvioSedeEntity;
import com.campuslands.ong.services.AyudaHumanitariaService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AyudaHumanitariaServiceImpl implements AyudaHumanitariaService {

    private RepositoryAyudaHumanitaria repositoryAyudaHumanitaria;
    private AyudaHumanitariaDTOConverter converter;
    private RepositoryEnvioSede repositoryEnvioSede;
    
    @Override
    @Transactional(readOnly = true)
    public List<AyudaHumanitariaDTO> findAll() {
        List<AyudaHumanitariaEntity> ayudashumanitarias= (List<AyudaHumanitariaEntity>)repositoryAyudaHumanitaria.findAll();
        return ayudashumanitarias.stream()
                                .map(ayudahumanitaria -> converter.convertAyudaHumanitariaDTO(ayudahumanitaria))
                                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AyudaHumanitariaDTO findById(Long id) {
        AyudaHumanitariaEntity ayudaHumanitaria= repositoryAyudaHumanitaria.findById(id).orElse(null);
        return converter.convertAyudaHumanitariaDTO(ayudaHumanitaria);
    }

    @Override
    @Transactional
    public AyudaHumanitariaDTO save(AyudaHumanitariaDTO ayudaHumanitaria) {

        Optional<EnvioSedeEntity> envioSedeOptional = repositoryEnvioSede.findById(ayudaHumanitaria.getId_envio_sede());

        if(envioSedeOptional.isPresent()){
            AyudaHumanitariaEntity ayudaHumanitariaEntity= converter.convertAyudaHumanitaria(ayudaHumanitaria);
            ayudaHumanitariaEntity.setEnvio_sede(envioSedeOptional.get());
            repositoryAyudaHumanitaria.save(ayudaHumanitariaEntity);
            return converter.convertAyudaHumanitariaDTO(ayudaHumanitariaEntity);
        }

       return null;
    }

    @Override
    @Transactional
    public AyudaHumanitariaEntity update(Long id, AyudaHumanitariaEntity ayudaHumanitaria) {
        Optional<AyudaHumanitariaEntity> ayudaHumanitariaCurrentOpyional=repositoryAyudaHumanitaria.findById(id);

        if(ayudaHumanitariaCurrentOpyional.isPresent()){
            AyudaHumanitariaEntity ayudaHumanitariaCurrent=ayudaHumanitariaCurrentOpyional.get();
            ayudaHumanitariaCurrent.setNum_voluntarios(ayudaHumanitaria.getNum_voluntarios());
            repositoryAyudaHumanitaria.save(ayudaHumanitariaCurrent);
            return ayudaHumanitariaCurrent;
        }

        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<AyudaHumanitariaEntity> ayudaHumanitariaOptional=repositoryAyudaHumanitaria.findById(id);
        if(ayudaHumanitariaOptional.isPresent()){
             repositoryAyudaHumanitaria.delete(ayudaHumanitariaOptional.get());
        }
    }
    
}
