package com.example.estacionamiento.microservicioTickets.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ticket")
public class ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @DecimalMax("9999.99")
    private float valor;

    @NotNull
    @PastOrPresent
    private LocalDate fecha;

    @NotBlank
    @Size(max = 20)
    private String patente;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    
}