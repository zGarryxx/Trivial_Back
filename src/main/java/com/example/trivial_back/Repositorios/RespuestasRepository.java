package com.example.trivial_back.Repositorios;

import com.example.trivial_back.Modelos.Respuestas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Esta interfaz se encarga de gestionar las respuestas de las preguntas
@Repository
public interface RespuestasRepository extends JpaRepository<Respuestas, Long> {
}