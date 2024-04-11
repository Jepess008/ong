package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.AyudaHumanitariaDTO;
import com.campuslands.ong.repositories.entities.AyudaHumanitariaEntity;

public interface AyudaHumanitariaService {

    List<AyudaHumanitariaDTO>findAll();

    AyudaHumanitariaDTO findById(Long id);

    AyudaHumanitariaDTO save(AyudaHumanitariaDTO ayudaHumanitaria);

    AyudaHumanitariaEntity update(Long id, AyudaHumanitariaEntity ayudaHumanitaria);

    void delete(Long id);
}
