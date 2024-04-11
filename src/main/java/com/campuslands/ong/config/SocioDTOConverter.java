package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.SocioDTO;
import com.campuslands.ong.repositories.entities.SocioEntity;

@Component
public class SocioDTOConverter {

    private ModelMapper dbm;

    public SocioDTOConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }

    public SocioDTO convertSocioDTO(SocioEntity socio){

        SocioDTO socioDTO= dbm.map(socio, SocioDTO.class);
        socioDTO.setId_persona(socio.getPersona().getId());
        socioDTO.setNombre_socio(socio.getPersona().getNombre() + " " +socio.getPersona().getApellido());
        socioDTO.setId_sede(socio.getSede().getId());
        socioDTO.setNombre_sede(socio.getSede().getDireccion());
        socioDTO.setId_tipo_cuota(socio.getTipo_cuota().getId());
        socioDTO.setNombre_cuota(socio.getTipo_cuota().getNombre());
        return socioDTO;


    }
    

    public SocioEntity convertSocio(SocioDTO socioDTO){

        SocioEntity socio=dbm.map(socioDTO,SocioEntity.class);
        return socio;
    }
}
