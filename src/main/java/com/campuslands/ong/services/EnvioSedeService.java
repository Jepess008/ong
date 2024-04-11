package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.EnvioSedeDTO;

public interface EnvioSedeService {

    List<EnvioSedeDTO>findAll();

    EnvioSedeDTO findById(Long id);

    EnvioSedeDTO save(EnvioSedeDTO envio_x_sede);

    void delete(Long id);
}
