package com.example.trivial_back.Enum;

import lombok.*;

@Getter
public enum Estado {
    CORRECTO(1),
    FALSO(0);

    private final int value;

    Estado(int value) {
        this.value = value;
    }

    public static Estado fromValue(int value) {
        for (Estado estado : values()) {
            if (estado.getValue() == value) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor de estado no válido: " + value);
    }
}