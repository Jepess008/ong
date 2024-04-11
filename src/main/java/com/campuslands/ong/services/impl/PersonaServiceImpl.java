package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.repositories.RepositoryPersona;
import com.campuslands.ong.repositories.entities.PersonaEntity;
import com.campuslands.ong.services.PersonaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService {


    private RepositoryPersona repositoryPersona;



    @Override
    @Transactional(readOnly = true)
    public List<PersonaEntity> findAll() {
        return (List<PersonaEntity>) repositoryPersona.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaEntity findById(Long id) {
        return repositoryPersona.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PersonaEntity save(PersonaEntity persona) {
        
        return repositoryPersona.save(persona);
    }

    @Override
    @Transactional
    public PersonaEntity update(Long id, PersonaEntity persona) {

        Optional<PersonaEntity> personaCurrentOptional=repositoryPersona.findById(id);

        if(personaCurrentOptional.isPresent()){
            PersonaEntity personaCurrent=personaCurrentOptional.get();
            personaCurrent.setNombre(persona.getNombre());
            repositoryPersona.save(personaCurrent);
            return personaCurrent;
        }

        return null;

    }

    @Override
    public void delete(Long id) {
        Optional<PersonaEntity> personOptional=repositoryPersona.findById(id);
        if(personOptional.isPresent()){
             repositoryPersona.delete(personOptional.get());
        }
    }
     
}
