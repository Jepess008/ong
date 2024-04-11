package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.EnvioDTO;

public interface EnvioService {
    
    List<EnvioDTO>findAll();

    EnvioDTO findById(Long id);

    EnvioDTO save(EnvioDTO envio);

    void delete(Long id);
}

