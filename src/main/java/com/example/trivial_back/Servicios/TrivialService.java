package com.example.trivial_back.Servicios;

import com.example.trivial_back.DTO.PreguntasDTO;
import com.example.trivial_back.DTO.PuntuacionDTO;
import com.example.trivial_back.Modelos.Categorias;
import com.example.trivial_back.Modelos.Preguntas;
import com.example.trivial_back.Modelos.Puntuacion;
import com.example.trivial_back.Repositorios.CategoriaRepository;
import com.example.trivial_back.Repositorios.PreguntasRepository;
import com.example.trivial_back.Repositorios.PuntuacionRepository;
import com.example.trivial_back.Repositorios.RespuestasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;

@Service
public class TrivialService {

    @Autowired
    private PreguntasRepository preguntasRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;


    // Metodo para crear una pregunta
    public Preguntas createPregunta(PreguntasDTO preguntaDTO) {
        if (!StringUtils.hasText(preguntaDTO.getEnunciado())) {
            throw new IllegalArgumentException("El enunciado de la pregunta no puede estar vacío");
        }
        if (preguntaDTO.getCategoria() == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
        Preguntas pregunta = new Preguntas();
        pregunta.setPregunta(preguntaDTO.getEnunciado());
        pregunta.setCategoria(preguntaDTO.getCategoria());
        return preguntasRepository.save(pregunta);
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
    public Preguntas updatePregunta(Long id, PreguntasDTO preguntaDetails) {

        Preguntas pregunta = preguntasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con el ID: " + id));
        if (!StringUtils.hasText(preguntaDetails.getEnunciado())) {
            throw new IllegalArgumentException("El enunciado de la pregunta no puede estar vacío");
        }
        if (preguntaDetails.getCategoria() == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
        pregunta.setPregunta(preguntaDetails.getEnunciado());
        pregunta.setCategoria(preguntaDetails.getCategoria());
        return preguntasRepository.save(pregunta);
    }

    // Metodo para eliminar una pregunta
    public void deletePregunta(Long id) {

        Preguntas pregunta = preguntasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con el ID: " + id));
        preguntasRepository.delete(pregunta);
    }

    // Metodo para obtener una pregunta aleatoria de una categoria
    public Preguntas getPreguntaAleatoria(String categoria) {
        if (!StringUtils.hasText(categoria)) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }
        Categorias categoriaEntity = categoriaRepository.findByNombre(categoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + categoria));
        return preguntasRepository.findRandomQuestionByCategory(categoriaEntity.getNombre());
    }

    // Metodo para obtener una categoria aleatoria
    public Categorias getCategoriaAleatoria() {
        return categoriaRepository.findRandomCategory();
    }

    // Metodo para puntuar una pregunta
    public int puntuarPregunta(Preguntas pregunta, String respuesta, Puntuacion puntuacion) {
        if (pregunta == null) {
            throw new IllegalArgumentException("La pregunta no puede ser nula");
        }
        if (!StringUtils.hasText(respuesta)) {
            throw new IllegalArgumentException("La respuesta no puede estar vacía");
        }
        if (puntuacion == null) {
            throw new IllegalArgumentException("La puntuación no puede ser nula");
        }
        if (pregunta.getPregunta().equalsIgnoreCase(respuesta)) {
            puntuacion.setPuntuacion(puntuacion.getPuntuacion() + 10);
        }
        return puntuacion.getPuntuacion();
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
    public Puntuacion guardarNombreUsuario(String nombreUsuario) {
        if (!StringUtils.hasText(nombreUsuario)) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setNombreUsuario(nombreUsuario);
        return puntuacionRepository.save(puntuacion);
    }

    // Metodo para contestar una pregunta
    public boolean contestarPregunta(Preguntas pregunta, String respuesta) {
        if (pregunta == null) {
            throw new IllegalArgumentException("La pregunta no puede ser nula");
        }
        if (!StringUtils.hasText(respuesta)) {
            throw new IllegalArgumentException("La respuesta no puede estar vacía");
        }
        if (!pregunta.getPregunta().equalsIgnoreCase(respuesta)) {
            throw new RuntimeException("La respuesta no es correcta");
        }
        return true;
    }
}