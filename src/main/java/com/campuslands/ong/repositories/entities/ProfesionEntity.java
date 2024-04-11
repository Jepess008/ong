package com.campuslands.ong.repositories.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Profesion")
public class ProfesionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "profesion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VoluntarioEntity> voluntario;

    @JsonIgnore
    /* Preguntar por esta relacion */
    @OneToMany(mappedBy = "profesion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AyudaHumanitariaEntity> Ayuda_Humanitaria;
}
