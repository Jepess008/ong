package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.SedeDTO;
import com.campuslands.ong.repositories.entities.SedeEntity;

@Component
public class SedeDTOConverter {
    
    private ModelMapper dbm;

    public SedeDTOConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }

    public SedeDTO convertSedeDTO(SedeEntity sede){
        
        SedeDTO sedeDTO = dbm.map(sede,SedeDTO.class);
        sedeDTO.setId_ciudad(sede.getCiudad().getId());
        sedeDTO.setNombre_ciudad(sede.getCiudad().getNombre());
        sedeDTO.setId_director(sede.getDirector().getId());
        sedeDTO.setNombre_director(sede.getDirector().getNombre() + " " + sede.getDirector().getApellido());
        return sedeDTO;
    }

    public SedeEntity convertSede(SedeDTO sedeDTO){

        SedeEntity sede= dbm.map(sedeDTO, SedeEntity.class);
        return sede;
    }
}
