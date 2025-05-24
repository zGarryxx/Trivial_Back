package com.example.trivial_back.Servicios;

import com.example.trivial_back.DTO.CRUD_PreguntasDTO;
import com.example.trivial_back.DTO.PuntuacionDTO;
import com.example.trivial_back.DTO.UsernameDTO;
import com.example.trivial_back.Modelos.Categorias;
import com.example.trivial_back.Modelos.Preguntas;
import com.example.trivial_back.Modelos.Puntuacion;
import com.example.trivial_back.Repositorios.CategoriaRepository;
import com.example.trivial_back.Repositorios.PreguntasRepository;
import com.example.trivial_back.Repositorios.PuntuacionRepository;
import com.example.trivial_back.Repositorios.RespuestasRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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

    @Autowired
    private PuntuacionRepository puntuacionRepository;

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

    @Test
    @DisplayName("Eliminar pregunta con ID válido y confirmar eliminación")
    @Tag("Preguntas")
    public void borrarPreguntaPositiva() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Deportes");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO galan = new CRUD_PreguntasDTO();
        galan.setEnunciado("¿Cual sera el juego del año en el 2027?");
        galan.setRespuestaCorrecta("GTA VI");
        galan.setCategoriaId(categoria.getId());
        trivialService.createPregunta(galan);

        Preguntas pregunta = preguntasRepository.findByPregunta("¿Cual sera el juego del año en el 2027?").orElseThrow();

        // WHEN
        String mensaje = trivialService.deletePregunta(pregunta.getId());

        // THEN
        Assertions.assertEquals("Pregunta eliminada correctamente", mensaje);
        Assertions.assertFalse(preguntasRepository.findById(pregunta.getId()).isPresent());
        Assertions.assertFalse(respuestasRepository.findByPreguntaId(pregunta.getId()).isPresent());
    }

    @Test
    @DisplayName("Eliminar pregunta con ID inexistente y recibir error")
    @Tag("Preguntas")
    public void borrarPreguntaNegativa() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Deportes");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO futbol = new CRUD_PreguntasDTO();
        futbol.setEnunciado("¿Quien es el jugador mas joven del Barcelona?");
        futbol.setRespuestaCorrecta("Lamine Yamal");
        futbol.setCategoriaId(categoria.getId());
        trivialService.createPregunta(futbol);

        Preguntas pregunta = preguntasRepository.findByPregunta("¿Quien es el jugador mas joven del Barcelona?").orElseThrow();
        Long idInexistente = pregunta.getId() + 1000;

        // WHEN
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> trivialService.deletePregunta(idInexistente));

        // THEN
        Assertions.assertEquals("Pregunta no encontrada con el ID: " + idInexistente, exception.getMessage());
    }

    @Test
    @DisplayName("Retornar lista de categorías válidas")
    @Tag("Categorias")
    public void obtenerAllCategoriasPositiva() {

        // GIVEN
        Categorias categoria1 = new Categorias();
        categoria1.setNombre("Ciencia");
        categoriaRepository.save(categoria1);

        Categorias categoria2 = new Categorias();
        categoria2.setNombre("Literatura");
        categoriaRepository.save(categoria2);

        // WHEN
        List<Categorias> categorias = trivialService.getAllCategorias();

        // THEN
        Assertions.assertNotNull(categorias);
        Assertions.assertEquals(2, categorias.size());
        Assertions.assertTrue(
                categorias.stream().anyMatch(c -> c.getNombre().equals("Ciencia"))
        );
        Assertions.assertTrue(
                categorias.stream().anyMatch(c -> c.getNombre().equals("Literatura"))
        );
    }

    @Test
    @DisplayName("Lista vacía si no hay categorías en la tabla Categorias en la BD")
    @Tag("Categorias")
    public void obtenerAllCategoriasNegativa() {

        // GIVEN

        // WHEN
        List<Categorias> categorias = trivialService.getAllCategorias();

        // THEN
        Assertions.assertNotNull(categorias);
        Assertions.assertTrue(categorias.isEmpty(), "No hay categorías disponibles");
    }

    @Test
    @DisplayName("Retornar pregunta al azar según la categoría elegida")
    @Tag("Preguntas")
    public void obtenerPreguntaAleatoriaPositiva() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Deportes");
        categoria = categoriaRepository.save(categoria);

        CRUD_PreguntasDTO futbol = new CRUD_PreguntasDTO();
        futbol.setEnunciado("¿Quien es el jugador mas joven del Barcelona?");
        futbol.setRespuestaCorrecta("Lamine Yamal");
        futbol.setCategoriaId(categoria.getId());
        trivialService.createPregunta(futbol);

        CRUD_PreguntasDTO tenis = new CRUD_PreguntasDTO();
        tenis.setEnunciado("¿Cuántos sets se juegan en un partido de Grand Slam?");
        tenis.setRespuestaCorrecta("5");
        tenis.setCategoriaId(categoria.getId());
        trivialService.createPregunta(tenis);


        CRUD_PreguntasDTO baloncesto = new CRUD_PreguntasDTO();
        baloncesto.setEnunciado("¿Cuántos jugadores hay en un equipo de baloncesto?");
        baloncesto.setRespuestaCorrecta("5");
        baloncesto.setCategoriaId(categoria.getId());
        trivialService.createPregunta(baloncesto);

        // WHEN
        var preguntaAleatoria = trivialService.getPreguntaAleatoria("Deportes");

        // THEN
        Assertions.assertNotNull(preguntaAleatoria);
        Assertions.assertTrue(
                preguntaAleatoria.getEnunciado().equals("¿Quien es el jugador mas joven del Barcelona?")
                        || preguntaAleatoria.getEnunciado().equals("¿Cuántos sets se juegan en un partido de Grand Slam?")
                        || preguntaAleatoria.getEnunciado().equals("¿Cuántos jugadores hay en un equipo de baloncesto?")
        );
    }

    @Test
    @DisplayName("No retornar pregunta si no hay preguntas en la categoría elegida")
    @Tag("Preguntas")
    public void obtenerPreguntaAleatoriaNegativa() {

        // GIVEN
        Categorias categoria = new Categorias();
        categoria.setNombre("Cine");
        categoria = categoriaRepository.save(categoria);

        // WHEN
        Exception exception = Assertions.assertThrows(Exception.class, () -> trivialService.getPreguntaAleatoria("Cine"));

        // THEN
        Assertions.assertNotNull(exception);
        Assertions.assertNotNull(categoria.getId());
        Assertions.assertEquals("Cine", categoria.getNombre());
    }

    @Test
    @DisplayName("Retornar una categoría válida aleatoriamente")
    @Tag("Categorias")
    public void obtenerCategoriaAleatoriaPositiva() {

        // GIVEN
        Categorias cine = new Categorias();
        cine.setNombre("Cine");
        categoriaRepository.save(cine);

        Categorias musica = new Categorias();
        musica.setNombre("Música");
        categoriaRepository.save(musica);

        Categorias literatura = new Categorias();
        literatura.setNombre("Literatura");
        categoriaRepository.save(literatura);

        // WHEN
        var ramdom = trivialService.getCategoriaAleatoria();

        // THEN
        Assertions.assertNotNull(ramdom);
        Assertions.assertTrue(
                ramdom.getNombre().equals("Cine") ||
                        ramdom.getNombre().equals("Música") ||
                        ramdom.getNombre().equals("Literatura")
        );
    }

    @Test
    @DisplayName("No retornar categoría si no hay categorías registradas")
    @Tag("Categorias")
    public void obtenerCategoriaAleatoriaNegativa() {

        // GIVEN

        // WHEN
        Exception exception = Assertions.assertThrows(Exception.class, () -> trivialService.getCategoriaAleatoria());

        // THEN
        Assertions.assertNotNull(exception);
    }

    @Test
    @DisplayName("Retornar lista de puntuaciones con usuarios que jugaron al trivial")
    @Tag("Puntuaciones")
    public void obtenerTodasPuntuacionesPositiva() {

        // GIVEN
        PuntuacionDTO Juanito = new PuntuacionDTO(
                LocalDateTime.now(),
                "Juanito",
                5,
                2,
                50
        );
        PuntuacionDTO Carlos = new PuntuacionDTO(
                LocalDateTime.now(),
                "Carlos",
                3,
                1,
                30
        );

        PuntuacionDTO Maria = new PuntuacionDTO(
                LocalDateTime.now(),
                "Maria",
                10,
                5,
                100
        );

        // Simula que el repositorio retorna estos datos
        puntuacionRepository.save(new Puntuacion(Juanito));
        puntuacionRepository.save(new Puntuacion(Carlos));
        puntuacionRepository.save(new Puntuacion(Maria));

        // WHEN
        List<PuntuacionDTO> puntuaciones = trivialService.getAllPuntuaciones();

        // THEN
        Assertions.assertNotNull(puntuaciones);
        Assertions.assertEquals(3, puntuaciones.size());
        Assertions.assertTrue(
                puntuaciones.stream().anyMatch(p -> p.getNombreUsuario().equals("Juanito"))
        );
        Assertions.assertTrue(
                puntuaciones.stream().anyMatch(p -> p.getNombreUsuario().equals("Carlos"))
        );
        Assertions.assertTrue(
                puntuaciones.stream().anyMatch(p -> p.getNombreUsuario().equals("Maria"))
        );
    }

    @Test
    @DisplayName("Lista vacía si no hay registros de usuarios que hayan jugado al trivial")
    @Tag("Puntuaciones")
    public void obtenerTodasPuntuacionesNegativa() {

        // GIVEN

        // WHEN
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> trivialService.getAllPuntuaciones());

        // THEN
        Assertions.assertEquals("No hay puntuaciones disponibles", exception.getMessage());
    }

    @Test
    @DisplayName("Guardar nombre de usuario válido")
    @Tag("Puntuaciones")
    public void guardarUsernamePositivo() {

        // GIVEN
        UsernameDTO Miguel = new UsernameDTO();
        Miguel.setNombreUsuario("Miguel");

        // WHEN
        PuntuacionDTO resultado = trivialService.guardarNombreUsuario(Miguel);

        // THEN
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Miguel", resultado.getNombreUsuario());
        Assertions.assertEquals(0, resultado.getAciertos());
        Assertions.assertEquals(0, resultado.getFallos());
        Assertions.assertEquals(0, resultado.getPuntuacion());
    }

    @Test
    @DisplayName("No guardar nombre de usuario vacío o nulo")
    @Tag("Puntuaciones")
    public void guardarUsernameNegativa() {

        // GIVEN
        UsernameDTO Luis = new UsernameDTO();
        Luis.setNombreUsuario("");
        UsernameDTO Gabriel = new UsernameDTO();
        Gabriel.setNombreUsuario(null);

        // WHEN
        Exception Vacio = Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.guardarNombreUsuario(Luis));
        Exception Nulo = Assertions.assertThrows(IllegalArgumentException.class, () -> trivialService.guardarNombreUsuario(Gabriel));

        // THEN
        Assertions.assertEquals("El nombre de usuario no puede estar vacío", Vacio.getMessage());
        Assertions.assertEquals("El nombre de usuario no puede estar vacío", Nulo.getMessage());
    }


}