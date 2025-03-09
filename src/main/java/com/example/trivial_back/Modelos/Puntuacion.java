package com.example.trivial_back.Modelos;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "puntuacion", schema = "trivial")
public class Puntuacion {

    // Atributos de la clase

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_usuario", length = 255)
    private String nombreUsuario;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @Column(name = "aciertos", columnDefinition = "INT DEFAULT 0")
    private Integer aciertos;

    @Column(name = "fallos", columnDefinition = "INT DEFAULT 0")
    private Integer fallos;

    @Column(name = "fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}