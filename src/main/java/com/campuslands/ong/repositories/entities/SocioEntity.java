package com.campuslands.ong.repositories.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Socio")
public class SocioEntity {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cuenta")
    private String cuenta;

    @Column(name = "fecha_pago")
    private Date fecha_pago;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_sede")
    @ManyToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY) 
    private PersonaEntity persona;


    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_tipo_cuota")
    @ManyToOne(fetch = FetchType.LAZY) 
    private Tipo_cuotaEntity tipo_cuota;
}
