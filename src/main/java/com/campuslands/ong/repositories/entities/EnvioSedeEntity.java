package com.campuslands.ong.repositories.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Envio_x_Sede")
public class EnvioSedeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_sede")
    @ManyToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;
    
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_envio")
    @ManyToOne(fetch = FetchType.LAZY)
    private EnvioEntity envio;

    @JsonIgnore
    @OneToMany(mappedBy = "envio_sede", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AyudaMateriialEntity> Ayuda_Material;

    @JsonIgnore
    @OneToMany(mappedBy = "envio_sede", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AyudaHumanitariaEntity> Ayuda_Humanitaria;

    
}
