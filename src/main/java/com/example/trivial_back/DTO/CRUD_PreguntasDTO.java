package com.example.trivial_back.DTO;

import lombok.*;

// Importamos las clases de lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class CRUD_PreguntasDTO {

    // Atributos de la clase
    private String enunciado;
    private String respuestaCorrecta;
    private Long categoriaId;

}