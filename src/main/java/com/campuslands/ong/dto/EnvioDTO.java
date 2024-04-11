package com.campuslands.ong.dto;

import java.sql.Date;

import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

/* Preguntar csi debo hacer DTO DE ENVIO POR SEDE, NECESITO ACLARAR
 * esa tabla intermedia ya que cuando yo este realizando el envio 
 * de algo como se manejaria
 */
@Data
public class EnvioDTO {
    @JsonView(EnvioDTO.class)
    private Long id;
    @JsonView(EnvioDTO.class)
    private Date fecha_salida;
    @JsonView(EnvioDTO.class)
    private String codigo;

    //listos
    private Long id_refugio;
    @JsonView(EnvioDTO.class)
    private String nombre_refugio;
    @JsonView(EnvioDTO.class)
    private String ciudad;

}
