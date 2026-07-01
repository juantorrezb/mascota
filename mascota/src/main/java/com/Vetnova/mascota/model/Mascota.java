package com.Vetnova.mascota.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    @Column(nullable = false)
    private String especie;

    private String raza;

    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID del cliente dueño es obligatorio")
    @Column(nullable = false)
    private Long clienteId;
}