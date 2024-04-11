package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.SedeDTO;
import com.campuslands.ong.repositories.entities.SedeEntity;

public interface SedeService {
    
    List<SedeDTO>findAllSe();

    SedeDTO findById(Long id);

    SedeDTO save(SedeDTO sede);

    SedeEntity update(Long id, SedeEntity sede);

    void delete(Long id);
}
