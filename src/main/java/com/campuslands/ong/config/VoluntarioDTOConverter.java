package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.VoluntarioDTO;
import com.campuslands.ong.repositories.entities.VoluntarioEntity;

@Component
public class VoluntarioDTOConverter {
    
    private ModelMapper dbm;

    public VoluntarioDTOConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }

    public VoluntarioDTO convertVoluntarioDTO(VoluntarioEntity voluntario){

        VoluntarioDTO voluntarioDTO= dbm.map(voluntario,VoluntarioDTO.class);
        voluntarioDTO.setId_persona(voluntario.getPersona().getId());
        voluntarioDTO.setNombre_voluntario(voluntario.getPersona().getNombre() +" "+ voluntario.getPersona().getApellido());
        voluntarioDTO.setId_sede(voluntario.getSede().getId());
        voluntarioDTO.setDireccion(voluntario.getSede().getDireccion());
        voluntarioDTO.setCiudad(voluntario.getSede().getCiudad().getNombre());

        if (voluntario.getProfesion() != null) {
            voluntarioDTO.setId_profesion(voluntario.getProfesion().getId());
            voluntarioDTO.setNombre_profesion(voluntario.getProfesion().getNombre());
        } else {
            
            voluntarioDTO.setId_profesion(null);
            voluntarioDTO.setNombre_profesion("ADMINISTRATIVO");
        }
        return voluntarioDTO;


    }

    public VoluntarioEntity convertVoluntario(VoluntarioDTO voluntarioDTO){

        VoluntarioEntity voluntario= dbm.map(voluntarioDTO,VoluntarioEntity.class);
        return voluntario;

    }


}
