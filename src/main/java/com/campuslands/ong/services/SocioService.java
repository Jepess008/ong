package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.SocioDTO;
import com.campuslands.ong.repositories.entities.SocioEntity;

public interface SocioService {

    List<SocioDTO>findAllS();

    SocioDTO findById(Long id);

    SocioDTO save(SocioDTO socio);

    SocioEntity update(Long id, SocioEntity socio);

    void delete(Long id);

    
}
