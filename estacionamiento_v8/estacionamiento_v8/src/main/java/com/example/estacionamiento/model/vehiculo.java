package com.example.estacionamiento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehiculo")
public class vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 255)
    private String marca;

    @NotBlank
    @Size(max = 100)
    private String modelo;

    @NotBlank
    @Size(max = 20)
    private String patente;

    @NotBlank
    @Size(max = 30)
    private String tipo;
    
}