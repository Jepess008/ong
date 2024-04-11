package com.campuslands.ong.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class AyudaHumanitariaDTO {
    
    @JsonView(AyudaHumanitariaDTO.class)
    private Long id;
    @JsonView(AyudaHumanitariaDTO.class)
    private int num_voluntarios;
    private Long id_profesion;
    @JsonView(AyudaHumanitariaDTO.class)
    private String nombre_profesion;
    private Long id_envio_sede;
    @JsonView(AyudaHumanitariaDTO.class)
    private Date fecha_salida;
    @JsonView(AyudaHumanitariaDTO.class)
    private String codigo;
    @JsonView(AyudaHumanitariaDTO.class)
    private String refugio;
    @JsonView(AyudaHumanitariaDTO.class)
    private String direccion_envio;
    @JsonView(AyudaHumanitariaDTO.class)
    private String ciudad;

}
