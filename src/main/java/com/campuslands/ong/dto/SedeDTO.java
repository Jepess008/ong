package com.campuslands.ong.dto;

import com.campuslands.ong.controllers.SedeController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class SedeDTO {
    
    @JsonView(SedeController.class)
    private Long id;
    @JsonView(SedeController.class)
    private String direccion;

    private Long id_director;
    @JsonView(SedeController.class)
    private String nombre_director;
    private Long id_ciudad;
    @JsonView(SedeController.class)
    private String nombre_ciudad;

}
