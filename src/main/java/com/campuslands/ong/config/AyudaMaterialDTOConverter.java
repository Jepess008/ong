package com.campuslands.ong.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.ong.dto.AyudaMaterialDTO;
import com.campuslands.ong.repositories.entities.AyudaMateriialEntity;

@Component
public class AyudaMaterialDTOConverter {
    
    private ModelMapper dbm;


    public AyudaMaterialDTOConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm=modelMapper;
    }


    public AyudaMaterialDTO convertAyudaMaterialDTO(AyudaMateriialEntity ayudaMaterial){
        
        AyudaMaterialDTO ayudaMaterialDTO= dbm.map(ayudaMaterial,AyudaMaterialDTO.class);
        ayudaMaterialDTO.setId_envioxsede(ayudaMaterial.getEnvio_sede().getId());
        ayudaMaterialDTO.setFecha_salida(ayudaMaterial.getEnvio_sede().getEnvio().getFecha_salida());
        ayudaMaterialDTO.setCodigo(ayudaMaterial.getEnvio_sede().getEnvio().getCodigo());
        ayudaMaterialDTO.setRefugio(ayudaMaterial.getEnvio_sede().getEnvio().getRefugio().getNombre());
        ayudaMaterialDTO.setDireccion_envio(ayudaMaterial.getEnvio_sede().getSede().getDireccion());
        ayudaMaterialDTO.setCiudad(ayudaMaterial.getEnvio_sede().getSede().getCiudad().getNombre());
        return ayudaMaterialDTO;
    }

    public AyudaMateriialEntity convertAyudaMaterial(AyudaMaterialDTO ayudaMaterialDTO){

        AyudaMateriialEntity ayudaMaterial= dbm.map(ayudaMaterialDTO, AyudaMateriialEntity.class);
        return ayudaMaterial;
    }


}
