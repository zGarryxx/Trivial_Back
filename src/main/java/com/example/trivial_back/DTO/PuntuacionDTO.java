package com.example.trivial_back.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PuntuacionDTO {

    // Atributos de la clase

    private java.time.LocalDateTime fecha;
    private String nombreUsuario;
    private int aciertos;
    private int fallos;
    private int puntuacion;
}