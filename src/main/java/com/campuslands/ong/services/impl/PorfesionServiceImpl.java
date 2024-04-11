package com.campuslands.ong.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.repositories.RepositoryProfesion;
import com.campuslands.ong.repositories.entities.ProfesionEntity;
import com.campuslands.ong.services.ProfesionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PorfesionServiceImpl implements ProfesionService {

    private RepositoryProfesion repositoryProfesion;

    @Override
    @Transactional(readOnly=true)
    public List<ProfesionEntity> findAll() {
       return(List<ProfesionEntity>) repositoryProfesion.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProfesionEntity findById(Long id) {
        return repositoryProfesion.findById(id).orElse(null);
    }

    @Override
    public ProfesionEntity save(ProfesionEntity profesion) {
        return repositoryProfesion.save(profesion);
    }

    @Override
    public ProfesionEntity update(Long id, ProfesionEntity profesion) {
        Optional<ProfesionEntity> profesionCurrentOptional =repositoryProfesion.findById(id);

        if(profesionCurrentOptional.isPresent()){
            ProfesionEntity profesionCurrent=profesionCurrentOptional.get();
            profesionCurrent.setNombre(profesion.getNombre());
            repositoryProfesion.save(profesionCurrent);
            return profesionCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<ProfesionEntity> profesionOptional= repositoryProfesion.findById(id);
        if (profesionOptional.isPresent()) {
                repositoryProfesion.delete(profesionOptional.get());
        }
    }
    
}
