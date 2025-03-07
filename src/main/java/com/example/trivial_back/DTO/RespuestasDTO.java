package com.example.trivial_back.DTO;

import com.example.trivial_back.Enum.Estado;
import lombok.*;

// Importamos las clases de lombok
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RespuestasDTO {

    // Atributos de la clase

    private PreguntasDTO pregunta;
    private String respuesta;
    private Estado estado;
}