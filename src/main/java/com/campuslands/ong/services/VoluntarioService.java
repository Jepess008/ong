package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.VoluntarioDTO;
import com.campuslands.ong.repositories.entities.VoluntarioEntity;

public interface VoluntarioService {

    List<VoluntarioDTO>findAll();

    VoluntarioDTO findById(Long id);

    VoluntarioDTO save(VoluntarioDTO voluntario);

    VoluntarioEntity update(Long id, VoluntarioEntity voluntario);

    void delete(Long id);
}
