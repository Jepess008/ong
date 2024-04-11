package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.RefugioDTO;
import com.campuslands.ong.repositories.entities.RefugioEntity;

@Component
public class RefugioDTOConverter {
    
    private ModelMapper dbm;

    public RefugioDTOConverter(ModelMapper modelMapper){

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }


    public RefugioDTO convertRefugioDTO(RefugioEntity refugio){

        RefugioDTO refugioDTO= dbm.map(refugio,RefugioDTO.class);
        refugioDTO.setId_ciudad(refugio.getCiudad().getId());
        refugioDTO.setNombre_ciudad(refugio.getCiudad().getNombre());
        return refugioDTO;

    }

    public RefugioEntity convertRefugio(RefugioDTO refugioDTO){
        RefugioEntity refugio= dbm.map(refugioDTO,RefugioEntity.class);
        return refugio;
    }
}
