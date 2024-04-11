package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.EnvioSedeDTO;
import com.campuslands.ong.repositories.entities.EnvioSedeEntity;

@Component
public class EnvioSedeDTOConverter {
    
    private ModelMapper dbm;

    public EnvioSedeDTOConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }


    public EnvioSedeDTO convertEnvioSedeDTO(EnvioSedeEntity enviosede){

        EnvioSedeDTO enviosedeDTO=dbm.map(enviosede, EnvioSedeDTO.class);
        enviosedeDTO.setId_sede(enviosede.getSede().getId());
        enviosedeDTO.setDireccion_sede(enviosede.getSede().getDireccion());
        enviosedeDTO.setCiudad_sede(enviosede.getSede().getCiudad().getNombre());
        enviosedeDTO.setId_envio(enviosede.getSede().getId());
        enviosedeDTO.setNombre_refugio(enviosede.getEnvio().getRefugio().getNombre());
        return enviosedeDTO;
    }

    public EnvioSedeEntity convertEnvioSede(EnvioSedeDTO enviosedeDTO){

        EnvioSedeEntity enviosede= dbm.map(enviosedeDTO,EnvioSedeEntity.class);
        return enviosede;
    }

}
