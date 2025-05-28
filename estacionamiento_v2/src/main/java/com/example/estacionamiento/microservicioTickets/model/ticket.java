package com.example.estacionamiento.microservicioTickets.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ticket")
public class ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 255)
    private float valor;

    @NotBlank
    @Size(max = 100)
    private LocalDateTime fecha;

    @NotBlank
    @Size(max = 20)
    private String patente;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    
}