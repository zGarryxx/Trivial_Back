package com.example.trivial_back.Servicios;

import com.example.trivial_back.DTO.*;
import com.example.trivial_back.Modelos.Categorias;
import com.example.trivial_back.Modelos.Preguntas;
import com.example.trivial_back.Modelos.Puntuacion;
import com.example.trivial_back.Modelos.Respuestas;
import com.example.trivial_back.Repositorios.CategoriaRepository;
import com.example.trivial_back.Repositorios.PreguntasRepository;
import com.example.trivial_back.Repositorios.PuntuacionRepository;
import com.example.trivial_back.Repositorios.RespuestasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TrivialService {

    private static final Logger logger = Logger.getLogger(TrivialService.class.getName());

    // Inyectamos los repositorios necesarios
    @Autowired
    private PreguntasRepository preguntasRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired
    private RespuestasRepository respuestasRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Long lastPreguntaId = null;

    // Metodo para crear una pregunta
    public Preguntas createPregunta(CRUD_PreguntasDTO preguntaDTO) {
        if (!StringUtils.hasText(preguntaDTO.getEnunciado())) {
            throw new IllegalArgumentException("El enunciado de la pregunta no puede estar vacío");
        }
        if (!StringUtils.hasText(preguntaDTO.getRespuestaCorrecta())) {
            throw new IllegalArgumentException("La respuesta correcta de la pregunta no puede estar vacía");
        }

        // Verificar que la categoría existe en la base de datos
        Categorias categoria = categoriaRepository.findById(preguntaDTO.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        // Verificar si la pregunta ya existe
        boolean preguntaExiste = preguntasRepository.existsByPreguntaAndCategoria(preguntaDTO.getEnunciado(), categoria);
        if (preguntaExiste) {
            throw new IllegalArgumentException("La pregunta ya existe en esta categoría");
        }

        Preguntas pregunta = new Preguntas();
        pregunta.setPregunta(preguntaDTO.getEnunciado());
        pregunta.setCategoria(categoria);
        Preguntas savedPregunta = preguntasRepository.save(pregunta);

        Respuestas respuestaCorrecta = new Respuestas();
        respuestaCorrecta.setPregunta(savedPregunta);
        respuestaCorrecta.setRespuesta(preguntaDTO.getRespuestaCorrecta());
        respuestasRepository.save(respuestaCorrecta);

        return savedPregunta;
    }

    // Metodo para obtener todas las preguntas
    public List<Preguntas> getAllPreguntas() {
        List<Preguntas> preguntas = preguntasRepository.findAll();
        if (preguntas.isEmpty()) {
            throw new RuntimeException("No hay preguntas disponibles");
        }
        return preguntas;
    }

    // Metodo para obtener una pregunta por id
    public Optional<Preguntas> getPreguntaById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        return Optional.of(preguntasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con el ID: " + id)));
    }

    // Metodo para actualizar una pregunta
    @Transactional
    public Preguntas updatePregunta(Long id, CRUD_PreguntasDTO preguntaDTO) {
        Preguntas pregunta = preguntasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con el ID: " + id));
        if (!StringUtils.hasText(preguntaDTO.getEnunciado())) {
            throw new IllegalArgumentException("El enunciado de la pregunta no puede estar vacío");
        }
        if (!StringUtils.hasText(preguntaDTO.getRespuestaCorrecta())) {
            throw new IllegalArgumentException("La respuesta correcta de la pregunta no puede estar vacía");
        }

        // Verificar que la categoría existe en la base de datos
        Categorias categoria = categoriaRepository.findById(preguntaDTO.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        pregunta.setPregunta(preguntaDTO.getEnunciado());
        pregunta.setCategoria(categoria);
        Preguntas updatedPregunta = preguntasRepository.save(pregunta);

        // Aquí actualizas la respuesta existente en vez de borrarla y crear una nueva
        Respuestas respuesta = respuestasRepository.findByPreguntaId(updatedPregunta.getId())
                .orElseThrow(() -> new RuntimeException("Respuesta correcta no encontrada para la pregunta ID: " + updatedPregunta.getId()));
        respuesta.setRespuesta(preguntaDTO.getRespuestaCorrecta());
        respuestasRepository.save(respuesta);

        return updatedPregunta;
    }

    // Metodo para eliminar una pregunta
    @Transactional
    public void deletePregunta(Long id) {
        Preguntas pregunta = preguntasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con el ID: " + id));

        respuestasRepository.deleteByPreguntaId(id);

        preguntasRepository.delete(pregunta);
    }

    // Metodo para obtener todas las categorias
    public List<Categorias> getAllCategorias() {
        List<Categorias> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            throw new RuntimeException("No hay categorías disponibles");
        }
        return categorias;
    }

    // Metodo para obtener una pregunta aleatoria de una categoria
    public PreguntasDTO getPreguntaAleatoria(String categoria) {
        String sql = "SELECT p.* FROM trivial.preguntas p " +
                "JOIN trivial.categorias c ON c.id = p.categoria_id " +
                "WHERE c.nombre = ? " +
                "ORDER BY random() " +
                "LIMIT 1";
        Preguntas pregunta;
        do {
            pregunta = jdbcTemplate.queryForObject(sql, new Object[]{categoria}, new BeanPropertyRowMapper<>(Preguntas.class));
        } while (pregunta != null && pregunta.getId().equals(lastPreguntaId));

        lastPreguntaId = pregunta != null ? pregunta.getId() : null;
        return new PreguntasDTO(pregunta.getId(), pregunta.getPregunta());
    }

    // Metodo para obtener una categoria aleatoria
    public CategoriaDTO getCategoriaAleatoria() {
        String sql = "SELECT * FROM trivial.categorias " +
                "ORDER BY random() " +
                "LIMIT 1";
        Categorias categoria = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Categorias.class));
        return new CategoriaDTO(categoria.getNombre());
    }

    // Metodo para listar las puntuaciones
    public List<PuntuacionDTO> getAllPuntuaciones() {
        List<PuntuacionDTO> puntuaciones = puntuacionRepository.findAllPuntuaciones();
        if (puntuaciones.isEmpty()) {
            throw new RuntimeException("No hay puntuaciones disponibles");
        }
        return puntuaciones;
    }

    // Metodo para guardar un nombre de usuario
    public PuntuacionDTO guardarNombreUsuario(UsernameDTO usernameDTO) {
        if (usernameDTO == null || !StringUtils.hasText(usernameDTO.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }

        String nombreUsuario = usernameDTO.getNombreUsuario();
        Optional<Puntuacion> existingPuntuacion = puntuacionRepository.findByNombreUsuario(nombreUsuario);
        if (existingPuntuacion.isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setNombreUsuario(nombreUsuario);
        puntuacion.setAciertos(0);
        puntuacion.setFallos(0);
        puntuacion.setFecha(LocalDateTime.now());
        puntuacion.setPuntuacion(0);

        Puntuacion savedPuntuacion = puntuacionRepository.save(puntuacion);

        return new PuntuacionDTO(
                savedPuntuacion.getFecha(),
                savedPuntuacion.getNombreUsuario(),
                savedPuntuacion.getAciertos(),
                savedPuntuacion.getFallos(),
                savedPuntuacion.getPuntuacion()
        );
    }

    // Metodo para contestar una pregunta
    public String contestarPregunta(PreguntasDTO preguntaDTO, String respuesta, String nombreUsuario) {
        if (preguntaDTO == null) {
            throw new IllegalArgumentException("La pregunta no puede ser nula");
        }
        if (!StringUtils.hasText(respuesta)) {
            throw new IllegalArgumentException("La respuesta no puede estar vacía");
        }

        Long preguntaId = getPreguntaIdByEnunciado(preguntaDTO.getEnunciado());

        Puntuacion puntuacion = puntuacionRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        logger.info("Buscando respuesta correcta para la pregunta ID: " + preguntaId);
        Respuestas respuestaCorrecta = respuestasRepository.findByPreguntaId(preguntaId)
                .orElseThrow(() -> new RuntimeException("Respuesta correcta no encontrada para la pregunta ID: " + preguntaId));
        if (respuestaCorrecta.getRespuesta().equalsIgnoreCase(respuesta)) {
            puntuacion.setAciertos(puntuacion.getAciertos() + 1);
            puntuacion.setPuntuacion(puntuacion.getPuntuacion() + 10);
            puntuacionRepository.save(puntuacion);
            return "¡Respuesta correcta! Has ganado 10 puntos.";
        } else {
            puntuacion.setFallos(puntuacion.getFallos() + 1);
            puntuacionRepository.save(puntuacion);
            return "Respuesta incorrecta. Inténtalo de nuevo.";
        }
    }

    // Metodo para obtener el ID de una pregunta por enunciado
    public Long getPreguntaIdByEnunciado(String enunciado) {
        if (!StringUtils.hasText(enunciado)) {
            throw new IllegalArgumentException("El enunciado no puede estar vacío");
        }

        Preguntas pregunta = preguntasRepository.findByPregunta(enunciado)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con el enunciado: " + enunciado));

        return pregunta.getId();
    }
}