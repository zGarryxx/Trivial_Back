package com.example.trivial_back.Modelos;

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
@Table(name = "preguntas", schema = "trivial")

public class Preguntas {

    // Atributos de la clase

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pregunta", nullable = false, columnDefinition = "TEXT")
    private String pregunta;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categoria;

    public void setCategoria(String categoria) {
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}