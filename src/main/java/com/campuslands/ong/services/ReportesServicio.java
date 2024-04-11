package com.campuslands.ong.services;

import java.util.List;

import com.campuslands.ong.dto.AyudaHumanitariaDTO;
import com.campuslands.ong.dto.AyudaMaterialDTO;
import com.campuslands.ong.dto.SedeDTO;
import com.campuslands.ong.dto.SocioDTO;
import com.campuslands.ong.dto.VoluntarioDTO;

public interface ReportesServicio {
    
    List<SocioDTO>informeSocios();

    List<SedeDTO>informeSedes();

    List<VoluntarioDTO>informeVoluntarios();

    List<AyudaMaterialDTO> informeAyudaMaterial();

    List<AyudaHumanitariaDTO> informeAyudaHumanitaria();

}
