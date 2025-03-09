package com.example.trivial_back.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

// Importamos las clases de lombok
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class PreguntasDTO {

    // Atributos de la clase

    @JsonIgnore
    private Long id;
    private String enunciado;

}
