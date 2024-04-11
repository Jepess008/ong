package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.EnvioDTO;
import com.campuslands.ong.repositories.entities.EnvioEntity;

@Component
public class EnvioDTOConverter {
    
    private ModelMapper dbm;

    public EnvioDTOConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }


    public EnvioDTO convertEnvioDTO(EnvioEntity envio){
        EnvioDTO envioDTO= dbm.map(envio,EnvioDTO.class);
        envioDTO.setId_refugio(envio.getRefugio().getId());
        envioDTO.setNombre_refugio(envio.getRefugio().getNombre());
        envioDTO.setCiudad(envio.getRefugio().getCiudad().getNombre());
        
        return envioDTO;
    }


    public EnvioEntity convertEnvio(EnvioDTO envioDTO){

        EnvioEntity envio=dbm.map(envioDTO,EnvioEntity.class);
        return envio;
    }
}
