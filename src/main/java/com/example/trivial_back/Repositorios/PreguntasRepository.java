package com.example.trivial_back.Repositorios;

import com.example.trivial_back.Modelos.Categorias;
import com.example.trivial_back.Modelos.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Long> {

    //  Méthod que devuelve si existe una pregunta en una categoría
    boolean existsByPreguntaAndCategoria(String pregunta, Categorias categoria);

    //  Méthod que devuelve una lista de preguntas de una categoría
    List<Preguntas> findAll();

    // Method que devuelve una pregunta por su pregunta
    Optional<Preguntas> findByPregunta(String pregunta);

}