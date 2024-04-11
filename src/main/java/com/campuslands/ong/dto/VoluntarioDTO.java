package com.campuslands.ong.dto;

import com.campuslands.ong.controllers.VoluntarioController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class VoluntarioDTO {
    
    @JsonView(VoluntarioController.class)
    private Long id;
    
    private Long id_persona;
    @JsonView(VoluntarioController.class)
    private String nombre_voluntario;
    @JsonView(VoluntarioController.class)
    private String tipo;
    @JsonView(VoluntarioController.class)
    private String disponibilidad;
    @JsonView(VoluntarioController.class)
    private Integer num_trabajos;
    private Long id_sede;
    @JsonView(VoluntarioController.class)
    private String direccion;
    @JsonView(VoluntarioController.class)
    private String ciudad;
    private Long id_profesion;
    @JsonView(VoluntarioController.class)
    private String nombre_profesion;

    
    
}
