package com.campuslands.ong.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.ong.dto.AyudaHumanitariaDTO;
import com.campuslands.ong.dto.AyudaMaterialDTO;
import com.campuslands.ong.dto.SedeDTO;
import com.campuslands.ong.dto.SocioDTO;
import com.campuslands.ong.dto.VoluntarioDTO;
import com.campuslands.ong.services.AyudaHumanitariaService;
import com.campuslands.ong.services.AyudaMaterialService;
import com.campuslands.ong.services.ReportesServicio;
import com.campuslands.ong.services.SedeService;
import com.campuslands.ong.services.SocioService;
import com.campuslands.ong.services.VoluntarioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportesServicioImpl implements ReportesServicio {

    private SocioService socioService;
    private SedeService sedeService;
    private VoluntarioService voluntarioService;
    private AyudaMaterialService ayudaMaterialService;
    private AyudaHumanitariaService ayudaHumanitariaService;

    
    @Override
    @Transactional(readOnly = true)
    public List<SocioDTO> informeSocios() {
        return (List<SocioDTO>) socioService.findAllS();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SedeDTO> informeSedes() {
        return (List<SedeDTO>) sedeService.findAllSe();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoluntarioDTO> informeVoluntarios() {
        return (List<VoluntarioDTO>) voluntarioService.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AyudaMaterialDTO> informeAyudaMaterial() {
       return (List<AyudaMaterialDTO>) ayudaMaterialService.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AyudaHumanitariaDTO> informeAyudaHumanitaria() {
        return (List<AyudaHumanitariaDTO>) ayudaHumanitariaService.findAll();
    }
    
}
