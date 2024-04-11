package com.campuslands.ong.dto;

import java.sql.Date;

import com.campuslands.ong.controllers.SocioController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class SocioDTO {
    
    @JsonView(SocioController.class)
    private Long id;
    private Long id_persona;
    @JsonView(SocioController.class)
    private String nombre_socio;
    @JsonView(SocioController.class)
    private String cuenta;
    @JsonView(SocioController.class)
    private Date fecha_pago;
    private Long id_sede;
    @JsonView(SocioController.class)
    private String nombre_sede;
    private Long id_tipo_cuota;
    @JsonView(SocioController.class)
    private String nombre_cuota;
    

}
