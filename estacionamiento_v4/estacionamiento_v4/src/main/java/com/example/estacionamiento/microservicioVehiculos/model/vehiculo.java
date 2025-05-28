package com.example.estacionamiento.microservicioVehiculos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
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

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}