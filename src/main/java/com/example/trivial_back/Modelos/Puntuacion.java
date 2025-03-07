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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 255)
    private String nombreUsuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categoria;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @Column(name = "aciertos", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer aciertos = 0;

    @Column(name = "fallos", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer fallos = 0;

    @Column(name = "fecha", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha = LocalDateTime.now();

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