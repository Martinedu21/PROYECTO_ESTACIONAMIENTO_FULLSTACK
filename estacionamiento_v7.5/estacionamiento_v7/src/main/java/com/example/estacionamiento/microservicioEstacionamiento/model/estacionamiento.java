package com.example.estacionamiento.microservicioEstacionamiento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String numeroEspacio;

    @NotBlank
    @Size(max = 20)
    private String patente;

    @Column(nullable = false)
    private boolean disponible;

    // Getters y setters

    public estacionamiento(String codigo, String string, String string2, int i) {
        //TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroEspacio() {
        return numeroEspacio;
    }

    public void setNumeroEspacio(String numeroEspacio) {
        this.numeroEspacio = numeroEspacio;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
     


}
