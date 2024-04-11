package com.campuslands.ong.dto;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class RefugioDTO {
    
    @JsonView(RefugioDTO.class)
    private Long id;
    @JsonView(RefugioDTO.class)
    private String nombre;
    private Long id_ciudad;
    @JsonView(RefugioDTO.class)
    private String nombre_ciudad;
    

}
