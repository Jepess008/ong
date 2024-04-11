package com.campuslands.ong.repositories.entities;

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
@Table(name= "Voluntario")
public class VoluntarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "disponibilidad")
    private String disponibilidad;

    @Column(name = "num_trabajos")
    private Integer num_trabajos;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_sede")
    @ManyToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonaEntity persona;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "id_profesion")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfesionEntity profesion;
  
}
