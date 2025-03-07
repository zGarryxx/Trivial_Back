package com.example.trivial_back.DTO;

import lombok.*;

// Importamos las clases de lombok
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class PreguntasDTO {

    private String enunciado;
    private String categoria;

    // Getter and Setter for enunciado
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    // Getter and Setter for categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
