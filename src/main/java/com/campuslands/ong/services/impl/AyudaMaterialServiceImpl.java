package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.config.AyudaMaterialDTOConverter;
import com.campuslands.ong.dto.AyudaMaterialDTO;
import com.campuslands.ong.repositories.RepositoryAyudaMaterial;
import com.campuslands.ong.repositories.RepositoryEnvioSede;
import com.campuslands.ong.repositories.entities.AyudaMateriialEntity;
import com.campuslands.ong.repositories.entities.EnvioSedeEntity;
import com.campuslands.ong.services.AyudaMaterialService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AyudaMaterialServiceImpl implements AyudaMaterialService{

    private RepositoryAyudaMaterial repositoryAyudaMaterial;
    private AyudaMaterialDTOConverter converter;
    private RepositoryEnvioSede repositoryEnvioSede;

    
    @Override
    @Transactional(readOnly = true)
    public List<AyudaMaterialDTO> findAll() {
        List<AyudaMateriialEntity> ayudasmateriales= (List<AyudaMateriialEntity>)repositoryAyudaMaterial.findAll();
        return ayudasmateriales.stream()
                                .map(ayudamaterial -> converter.convertAyudaMaterialDTO(ayudamaterial))
                                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AyudaMaterialDTO findById(Long id) {
        AyudaMateriialEntity ayudaMaterial= repositoryAyudaMaterial.findById(id).orElse(null);
        return converter.convertAyudaMaterialDTO(ayudaMaterial);
    }

    @Override
    @Transactional
    public AyudaMaterialDTO save(AyudaMaterialDTO ayudaMaterial) {

        Optional<EnvioSedeEntity> envioSedeOptional = repositoryEnvioSede.findById(ayudaMaterial.getId_envioxsede());

        if(envioSedeOptional.isPresent()){
            AyudaMateriialEntity ayudaMateriialEntity= converter.convertAyudaMaterial(ayudaMaterial);
            ayudaMateriialEntity.setEnvio_sede(envioSedeOptional.get());
            repositoryAyudaMaterial.save(ayudaMateriialEntity);
            return converter.convertAyudaMaterialDTO(ayudaMateriialEntity);
        }

       return null;
    }

    @Override
    @Transactional
    public AyudaMateriialEntity update(Long id, AyudaMateriialEntity ayudaMaterial) {
        Optional<AyudaMateriialEntity> ayudaMaterialCurrentOptional=repositoryAyudaMaterial.findById(id);

        if(ayudaMaterialCurrentOptional.isPresent()){
            AyudaMateriialEntity ayudaMaterialCurrent=ayudaMaterialCurrentOptional.get();
            ayudaMaterialCurrent.setCantidad(ayudaMaterial.getCantidad());
            ayudaMaterialCurrent.setDescripcion(ayudaMaterial.getDescripcion());
            repositoryAyudaMaterial.save(ayudaMaterialCurrent);
            return ayudaMaterialCurrent;
        }

        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
         Optional<AyudaMateriialEntity> ayudaMaterialOptional=repositoryAyudaMaterial.findById(id);
       if(ayudaMaterialOptional.isPresent()){
            repositoryAyudaMaterial.delete(ayudaMaterialOptional.get());
       }
    }
    
}
