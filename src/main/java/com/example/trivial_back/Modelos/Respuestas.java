package com.example.trivial_back.Modelos;

import com.example.trivial_back.Enum.Estado;
import jakarta.persistence.*;
import lombok.*;

// Metemos la anotación @Data para que Lombok genere los getters y setters

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "respuestas", schema = "trivial")

public class Respuestas {

    // Atributos de la clase

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "respuesta", nullable = false, columnDefinition = "TEXT")
    private String respuesta;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Preguntas pregunta;
}