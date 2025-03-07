package com.example.trivial_back.Repositorios;

import com.example.trivial_back.Modelos.Preguntas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PreguntasRepository extends CrudRepository<Preguntas, Long> {

    @Query("SELECT p FROM Preguntas p WHERE p.categoria.nombre = :categoria ORDER BY RAND()")
    Preguntas findRandomQuestionByCategory(String categoria);

    List<Preguntas> findAll();
}