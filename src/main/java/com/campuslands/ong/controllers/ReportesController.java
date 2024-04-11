package com.campuslands.ong.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.ong.dto.AyudaHumanitariaDTO;
import com.campuslands.ong.dto.AyudaMaterialDTO;
import com.campuslands.ong.dto.SedeDTO;
import com.campuslands.ong.dto.SocioDTO;
import com.campuslands.ong.dto.VoluntarioDTO;
import com.campuslands.ong.services.ReportesServicio;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/reportes")
@AllArgsConstructor
public class ReportesController {
    
    private ReportesServicio reportesServicio;

    @GetMapping("/socios")
     @JsonView(SocioController.class)
    public List<SocioDTO> findAllS(){
        return reportesServicio.informeSocios();
    }

    @GetMapping("/sedes")
    @JsonView(SedeController.class)
    public List<SedeDTO> findAllSe(){
        return reportesServicio.informeSedes();
    }


    @GetMapping("/voluntarios")
    @JsonView(VoluntarioController.class)
    public List<VoluntarioDTO> findAllV(){
        return reportesServicio.informeVoluntarios();
    }


    @GetMapping("/ayudasmateriales")
    @JsonView(AyudaMaterialDTO.class)
    public List<AyudaMaterialDTO> findAllM(){
        return reportesServicio.informeAyudaMaterial();
    }

    @GetMapping("/ayudashumanitarias")
    @JsonView(AyudaHumanitariaDTO.class)
    public List<AyudaHumanitariaDTO> findAllH(){
        return reportesServicio.informeAyudaHumanitaria();
    }

   

    
    
}

