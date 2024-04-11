package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.AyudaHumanitariaDTO;
import com.campuslands.ong.repositories.entities.AyudaHumanitariaEntity;

@Component
public class AyudaHumanitariaDTOConverter {
    
    private ModelMapper dbm;

    public AyudaHumanitariaDTOConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }

    public AyudaHumanitariaDTO convertAyudaHumanitariaDTO(AyudaHumanitariaEntity ayudaHumanitaria) {
        AyudaHumanitariaDTO ayudaHumanitariaDTO = dbm.map(ayudaHumanitaria, AyudaHumanitariaDTO.class);
        ayudaHumanitariaDTO.setId_envio_sede(ayudaHumanitaria.getEnvio_sede().getId());
        ayudaHumanitariaDTO.setId_profesion(ayudaHumanitaria.getProfesion().getId());
        ayudaHumanitariaDTO.setNombre_profesion(ayudaHumanitaria.getProfesion().getNombre());
        ayudaHumanitariaDTO.setFecha_salida(ayudaHumanitaria.getEnvio_sede().getEnvio().getFecha_salida());
        ayudaHumanitariaDTO.setCodigo(ayudaHumanitaria.getEnvio_sede().getEnvio().getCodigo());
        ayudaHumanitariaDTO.setRefugio(ayudaHumanitaria.getEnvio_sede().getEnvio().getRefugio().getNombre());
        ayudaHumanitariaDTO.setDireccion_envio(ayudaHumanitaria.getEnvio_sede().getSede().getDireccion());
        ayudaHumanitariaDTO.setCiudad(ayudaHumanitaria.getEnvio_sede().getSede().getCiudad().getNombre());
        return ayudaHumanitariaDTO;

    }

    public AyudaHumanitariaEntity convertAyudaHumanitaria(AyudaHumanitariaDTO ayudaHumanitariaDTO){

        AyudaHumanitariaEntity ayudaHumanitaria= dbm.map(ayudaHumanitariaDTO, AyudaHumanitariaEntity.class);
        return ayudaHumanitaria;
    }
    
}
