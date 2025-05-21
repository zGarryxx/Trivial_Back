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

        // WHEN & THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.createPregunta(dto));
    }
}