package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.campuslands.ong.config.VoluntarioDTOConverter;
import com.campuslands.ong.dto.VoluntarioDTO;
import com.campuslands.ong.repositories.RepositoryPersona;
import com.campuslands.ong.repositories.RepositoryProfesion;
import com.campuslands.ong.repositories.RepositorySede;
import com.campuslands.ong.repositories.RepositoryVoluntario;
import com.campuslands.ong.repositories.entities.PersonaEntity;
import com.campuslands.ong.repositories.entities.ProfesionEntity;
import com.campuslands.ong.repositories.entities.SedeEntity;
import com.campuslands.ong.repositories.entities.VoluntarioEntity;
import com.campuslands.ong.services.VoluntarioService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class VoluntarioServiceImpl implements VoluntarioService{

    private RepositoryVoluntario repositoryVoluntario;
    private VoluntarioDTOConverter converter;
    private RepositorySede repositorySede;
    private RepositoryPersona repositoryPersona;
    private RepositoryProfesion repositoryProfesion;

    @Override
    public List<VoluntarioDTO> findAll() {
        List<VoluntarioEntity> voluntarios= (List<VoluntarioEntity>)repositoryVoluntario.findAll();
        return voluntarios.stream()
                            .map(voluntario -> converter.convertVoluntarioDTO(voluntario))
                            .toList();
    }

    @Override
    public VoluntarioDTO findById(Long id) {
        VoluntarioEntity voluntario= repositoryVoluntario.findById(id).orElse(null);
        return converter.convertVoluntarioDTO(voluntario);
    }

    @Override
    public VoluntarioDTO save(VoluntarioDTO voluntario) {
        
        Optional<PersonaEntity> personaOptional= repositoryPersona.findById(voluntario.getId_persona());
        Optional<SedeEntity> sedeOptional= repositorySede.findById(voluntario.getId_sede());
        Optional<ProfesionEntity>profesionOptional= repositoryProfesion.findById(voluntario.getId_profesion());

        if(personaOptional.isPresent() && sedeOptional.isPresent() && profesionOptional.isPresent()){
            VoluntarioEntity voluntarioEntity= converter.convertVoluntario(voluntario);
            voluntarioEntity.setPersona(personaOptional.get());
            voluntarioEntity.setSede(sedeOptional.get());
            voluntarioEntity.setProfesion(profesionOptional.get());
            repositoryVoluntario.save(voluntarioEntity);
            return converter.convertVoluntarioDTO(voluntarioEntity);
        }else{
            return null;
        }

        

    }

    @Override
    public VoluntarioEntity update(Long id, VoluntarioEntity voluntario) {
       Optional<VoluntarioEntity> voluntarioCurrentOptional=repositoryVoluntario.findById(id);

        if(voluntarioCurrentOptional.isPresent()){
            VoluntarioEntity voluntariCurrent=voluntarioCurrentOptional.get();
            voluntariCurrent.setDisponibilidad(voluntario.getDisponibilidad());
            voluntariCurrent.setNum_trabajos(voluntario.getNum_trabajos());
            repositoryVoluntario.save(voluntariCurrent);
            return voluntariCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<VoluntarioEntity> voluntarioOptional=repositoryVoluntario.findById(id);
       if(voluntarioOptional.isPresent()){
            repositoryVoluntario.delete(voluntarioOptional.get());
       }
    }
    
}
