package com.campuslands.ong.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class AyudaMaterialDTO {
    
    @JsonView(AyudaMaterialDTO.class)
    private Long id;
    @JsonView(AyudaMaterialDTO.class)
    private String tipo;
    @JsonView(AyudaMaterialDTO.class)
    private String cantidad;
    @JsonView(AyudaMaterialDTO.class)
    private String descripcion;
    private Long id_envioxsede;
    @JsonView(AyudaMaterialDTO.class)
    private Date fecha_salida;
    @JsonView(AyudaMaterialDTO.class)
    private String codigo;
    @JsonView(AyudaMaterialDTO.class)
    private String refugio;
    @JsonView(AyudaMaterialDTO.class)
    private String direccion_envio;
    @JsonView(AyudaMaterialDTO.class)
    private String ciudad;
    

}
