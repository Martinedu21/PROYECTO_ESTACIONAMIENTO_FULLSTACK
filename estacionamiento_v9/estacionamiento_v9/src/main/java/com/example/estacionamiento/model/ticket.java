package com.example.estacionamiento.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 30)
    private String tipo;

    @NotNull
    @DecimalMax("9999.99")
    private float valor;

    @NotNull
    @PastOrPresent
    private LocalDateTime fecha;

    //private LocalDate solo_fecha(LocalDateTime fecha){
    //    return this.fecha.toLocalDate();
    //}

    @NotBlank
    @Size(max = 20)
    private String patente;
    
}