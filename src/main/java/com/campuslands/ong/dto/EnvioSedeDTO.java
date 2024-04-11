package com.campuslands.ong.dto;


import com.campuslands.ong.controllers.EnvioSedeController;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class EnvioSedeDTO {

    @JsonView(EnvioSedeController.class)
    private Long id;
    private Long id_sede;
    @JsonView(EnvioSedeController.class)
    private String direccion_sede;
    @JsonView(EnvioSedeController.class)
    private String ciudad_sede;
    private Long id_envio;
    @JsonView(EnvioSedeController.class)
    private String nombre_refugio;

    
}
