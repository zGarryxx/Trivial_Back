package com.example.trivial_back.Servicios;

import com.example.trivial_back.DTO.CRUD_PreguntasDTO;
import com.example.trivial_back.Modelos.Categorias;
import com.example.trivial_back.Modelos.Preguntas;
import com.example.trivial_back.Repositorios.CategoriaRepository;
import com.example.trivial_back.Repositorios.PreguntasRepository;
import com.example.trivial_back.Repositorios.RespuestasRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")

public class TrivialServiceTest {

    @Autowired
    private TrivialService trivialService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PreguntasRepository preguntasRepository;

    @Autowired
    private RespuestasRepository respuestasRepository;

    @Test
    @DisplayName("Test de crear pregunta positivo")
    @Tag("Preguntas")
    public void crearPreguntaPositiva() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Geografía");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO dto = new CRUD_PreguntasDTO();
        dto.setEnunciado("¿Capital de Francia?");
        dto.setRespuestaCorrecta("París");
        dto.setCategoriaId(categoria.getId());

        // WHEN
        Preguntas resultado = trivialService.createPregunta(dto);

        // THEN
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("¿Capital de Francia?", resultado.getPregunta());
        Assertions.assertEquals(categoria.getId(), resultado.getCategoria().getId());
        Assertions.assertTrue(respuestasRepository.findByPreguntaId(resultado.getId()).isPresent());
    }

    @Test
    @DisplayName("Test de crear pregunta negativo")
    @Tag("Preguntas")
    public void crearPreguntaNegativa() {

        // GIVEN
        CRUD_PreguntasDTO dto = new CRUD_PreguntasDTO();
        dto.setEnunciado("");
        dto.setRespuestaCorrecta("París");
        dto.setCategoriaId(1L);

        // WHEN
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.createPregunta(dto));

        // THEN
        Assertions.assertNotNull(exception);
        Assertions.assertEquals("El enunciado de la pregunta no puede estar vacío", exception.getMessage());
    }

    @Test
    @DisplayName("Test obtener todas las preguntas - positivo con dos preguntas usando DTO")
    @Tag("Preguntas")
    public void obtenerAllPreguntasPositiva() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Historia");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO dto1 = new CRUD_PreguntasDTO();
        dto1.setEnunciado("¿Quién descubrió América?");
        dto1.setRespuestaCorrecta("Cristóbal Colón");
        dto1.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto1);

        CRUD_PreguntasDTO dto2 = new CRUD_PreguntasDTO();
        dto2.setEnunciado("¿En qué año llegó el hombre a la Luna?");
        dto2.setRespuestaCorrecta("1969");
        dto2.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto2);

        // WHEN
        List<Preguntas> preguntas = trivialService.getAllPreguntas();

        // THEN
        Assertions.assertNotNull(preguntas);
        Assertions.assertEquals(2, preguntas.size());
        Assertions.assertTrue(
                preguntas.stream().anyMatch(p -> p.getPregunta().equals("¿Quién descubrió América?"))
        );
        Assertions.assertTrue(
                preguntas.stream().anyMatch(p -> p.getPregunta().equals("¿En qué año llegó el hombre a la Luna?"))
        );
    }

    @Test
    @DisplayName("Test obtener todas las preguntas - negativo (lista vacía)")
    @Tag("Preguntas")
    public void obtenerAllPreguntasNegativa() {

        // WHEN
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> trivialService.getAllPreguntas());

        // THEN
        Assertions.assertEquals("No hay preguntas disponibles", exception.getMessage());
    }

    @Test
    @DisplayName("Test obtener pregunta por ID válido - positivo con varios ejemplos")
    @Tag("Preguntas")
    public void obtenerPreguntasByIdPositiva() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Deportes");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO dto1 = new CRUD_PreguntasDTO();
        dto1.setEnunciado("¿Cuántos jugadores tiene un equipo de fútbol?");
        dto1.setRespuestaCorrecta("11");
        dto1.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto1);

        CRUD_PreguntasDTO dto2 = new CRUD_PreguntasDTO();
        dto2.setEnunciado("¿En qué país se originaron los Juegos Olímpicos?");
        dto2.setRespuestaCorrecta("Grecia");
        dto2.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto2);

        // WHEN
        Preguntas pregunta1 = preguntasRepository.findByPregunta("¿Cuántos jugadores tiene un equipo de fútbol?").orElseThrow();
        Preguntas pregunta2 = preguntasRepository.findByPregunta("¿En qué país se originaron los Juegos Olímpicos?").orElseThrow();

        Optional<Preguntas> resultado1 = trivialService.getPreguntaById(pregunta1.getId());
        Optional<Preguntas> resultado2 = trivialService.getPreguntaById(pregunta2.getId());

        // THEN
        Assertions.assertTrue(resultado1.isPresent());
        Assertions.assertEquals("¿Cuántos jugadores tiene un equipo de fútbol?", resultado1.get().getPregunta());
        Assertions.assertEquals(categoria.getId(), resultado1.get().getCategoria().getId());

        Assertions.assertTrue(resultado2.isPresent());
        Assertions.assertEquals("¿En qué país se originaron los Juegos Olímpicos?", resultado2.get().getPregunta());
        Assertions.assertEquals(categoria.getId(), resultado2.get().getCategoria().getId());
    }

    @Test
    @DisplayName("Test obtener pregunta por ID inexistente - negativo, con datos previos usando DTO")
    @Tag("Preguntas")
    public void obtenerPreguntasByIdNegativa() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Arte");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO dto1 = new CRUD_PreguntasDTO();
        dto1.setEnunciado("¿Quién pintó la Mona Lisa?");
        dto1.setRespuestaCorrecta("Leonardo da Vinci");
        dto1.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto1);

        CRUD_PreguntasDTO dto2 = new CRUD_PreguntasDTO();
        dto2.setEnunciado("¿En qué museo está la Mona Lisa?");
        dto2.setRespuestaCorrecta("Museo del Louvre");
        dto2.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto2);

        Preguntas pregunta1 = preguntasRepository.findByPregunta("¿Quién pintó la Mona Lisa?").orElseThrow();
        Preguntas pregunta2 = preguntasRepository.findByPregunta("¿En qué museo está la Mona Lisa?").orElseThrow();

        Long idExistente1 = pregunta1.getId();
        Long idExistente2 = pregunta2.getId();
        Long idInexistente = Math.max(idExistente1, idExistente2) + 1000;
        Long idNegativo = -5L;
        Long idNulo = null;

        // WHEN
        Exception ex1 = Assertions.assertThrows(RuntimeException.class, () -> trivialService.getPreguntaById(idInexistente));
        Exception ex2 = Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.getPreguntaById(idNegativo));
        Exception ex3 = Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.getPreguntaById(idNulo));

        // THEN
        Assertions.assertEquals("Pregunta no encontrada con el ID: " + idInexistente, ex1.getMessage());
        Assertions.assertEquals("El ID debe ser un número positivo", ex2.getMessage());
        Assertions.assertEquals("El ID debe ser un número positivo", ex3.getMessage());
    }

    @Test
    @DisplayName("Actualizar pregunta correctamente con todos los campos")
    @Tag("Preguntas")
    public void actualizarPreguntaPositiva() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Deportes");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO dto1 = new CRUD_PreguntasDTO();
        dto1.setEnunciado("¿Cuántos jugadores tiene un equipo de fútbol?");
        dto1.setRespuestaCorrecta("11");
        dto1.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto1);

        CRUD_PreguntasDTO dto2 = new CRUD_PreguntasDTO();
        dto2.setEnunciado("¿En qué país se originaron los Juegos Olímpicos?");
        dto2.setRespuestaCorrecta("Grecia");
        dto2.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto2);

        Preguntas pregunta = preguntasRepository.findByPregunta("¿Cuántos jugadores tiene un equipo de fútbol?").orElseThrow();

        CRUD_PreguntasDTO updateDTO = new CRUD_PreguntasDTO();
        updateDTO.setEnunciado("¿Cuántos jugadores hay en un partido de padel?");
        updateDTO.setRespuestaCorrecta("4 en total, 2 en cada lado de la pista");
        updateDTO.setCategoriaId(categoria.getId());

        // WHEN
        Preguntas actualizada = trivialService.updatePregunta(pregunta.getId(), updateDTO);

        // THEN
        Assertions.assertNotNull(actualizada);
        Assertions.assertEquals("¿Cuántos jugadores hay en un partido de padel?", actualizada.getPregunta());
        Assertions.assertEquals(categoria.getId(), actualizada.getCategoria().getId());
        Assertions.assertTrue(respuestasRepository.findByPreguntaId(actualizada.getId()).isPresent());
        Assertions.assertEquals("4 en total, 2 en cada lado de la pista", respuestasRepository.findByPreguntaId(actualizada.getId()).get().getRespuesta());
    }

    @Test
    @DisplayName("Intentar actualizar con ID inexistente o datos inválidos")
    @Tag("Preguntas")
    public void actualizarPreguntaNegativa() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Deportes");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO dto1 = new CRUD_PreguntasDTO();
        dto1.setEnunciado("¿Cuántos jugadores tiene un equipo de fútbol?");
        dto1.setRespuestaCorrecta("11");
        dto1.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto1);

        CRUD_PreguntasDTO dto2 = new CRUD_PreguntasDTO();
        dto2.setEnunciado("¿En qué país se originaron los Juegos Olímpicos?");
        dto2.setRespuestaCorrecta("Grecia");
        dto2.setCategoriaId(categoria.getId());
        trivialService.createPregunta(dto2);

        Preguntas pregunta = preguntasRepository.findByPregunta("¿Cuántos jugadores tiene un equipo de fútbol?").orElseThrow();

        CRUD_PreguntasDTO updateDTOvacio = new CRUD_PreguntasDTO();
        updateDTOvacio.setEnunciado("");
        updateDTOvacio.setRespuestaCorrecta("4 en total, 2 en cada lado de la pista");
        updateDTOvacio.setCategoriaId(categoria.getId());

        CRUD_PreguntasDTO updateDTOSinRespuesta = new CRUD_PreguntasDTO();
        updateDTOSinRespuesta.setEnunciado("¿Cuántos jugadores hay en un partido de padel?");
        updateDTOSinRespuesta.setRespuestaCorrecta("");
        updateDTOSinRespuesta.setCategoriaId(categoria.getId());

        Long idInexistente = pregunta.getId() + 1000;

        // WHEN
        Exception ex1 = Assertions.assertThrows(RuntimeException.class, () -> trivialService.updatePregunta(idInexistente, updateDTOvacio));
        Exception ex2 = Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.updatePregunta(pregunta.getId(), updateDTOvacio));
        Exception ex3 = Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.updatePregunta(pregunta.getId(), updateDTOSinRespuesta));

        // THEN
        Assertions.assertEquals("Pregunta no encontrada con el ID: " + idInexistente, ex1.getMessage());
        Assertions.assertEquals("El enunciado de la pregunta no puede estar vacío", ex2.getMessage());
        Assertions.assertEquals("La respuesta correcta de la pregunta no puede estar vacía", ex3.getMessage());
    }

}