package com.example.trivial_back.Controladores;

import com.example.trivial_back.DTO.PreguntasDTO;
import com.example.trivial_back.DTO.PuntuacionDTO;
import com.example.trivial_back.Modelos.Categorias;
import com.example.trivial_back.Modelos.Preguntas;
import com.example.trivial_back.Modelos.Puntuacion;
import com.example.trivial_back.Servicios.TrivialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trivial")
public class TrivialController {

    @Autowired
    private TrivialService trivialService;

    // Endpoint para crear una pregunta
    @PostMapping("/pregunta/crear")
    public ResponseEntity<Preguntas> createPregunta(@RequestBody PreguntasDTO preguntaDTO) {
        Preguntas nuevaPregunta = trivialService.createPregunta(preguntaDTO);
        return ResponseEntity.ok(nuevaPregunta);
    }

    // Endpoint para obtener todas las preguntas
    @GetMapping("/preguntas")
    public ResponseEntity<List<Preguntas>> getAllPreguntas() {
        List<Preguntas> preguntas = trivialService.getAllPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    // Endpoint para obtener una pregunta por id
    @GetMapping("/pregunta/{id}")
    public ResponseEntity<Preguntas> getPreguntaById(@PathVariable Long id) {
        Optional<Preguntas> pregunta = trivialService.getPreguntaById(id);
        return pregunta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para actualizar una pregunta
    @PutMapping("/pregunta/actualizar/{id}")
    public ResponseEntity<Preguntas> updatePregunta(@PathVariable Long id, @RequestBody PreguntasDTO preguntaDetails) {
        Preguntas actualizadaPregunta = trivialService.updatePregunta(id, preguntaDetails);
        return ResponseEntity.ok(actualizadaPregunta);
    }

    // Endpoint para eliminar una pregunta
    @DeleteMapping("/pregunta/eliminar/{id}")
    public ResponseEntity<Void> deletePregunta(@PathVariable Long id) {
        trivialService.deletePregunta(id);
        return ResponseEntity.ok().build();
    }

    // Endpoint para obtener una pregunta aleatoria de una categoria
    @GetMapping("/pregunta/aleatoria/{categoria}")
    public ResponseEntity<Preguntas> getPreguntaAleatoria(@PathVariable String categoria) {
        Preguntas pregunta = trivialService.getPreguntaAleatoria(categoria);
        return ResponseEntity.ok(pregunta);
    }

    // Endpoint para obtener una categoria aleatoria
    @GetMapping("/categoria/aleatoria")
    public ResponseEntity<Categorias> getCategoriaAleatoria() {
        Categorias categoria = trivialService.getCategoriaAleatoria();
        return ResponseEntity.ok(categoria);
    }

    // Endpoint para puntuar una pregunta
    @PostMapping("/pregunta/puntuar")
    public ResponseEntity<Integer> puntuarPregunta(@RequestBody Preguntas pregunta, @RequestParam String respuesta, @RequestBody Puntuacion puntuacion) {
        int puntos = trivialService.puntuarPregunta(pregunta, respuesta, puntuacion);
        return ResponseEntity.ok(puntos);
    }

    // Endpoint para listar las puntuaciones
    @GetMapping("/puntuaciones")
    public ResponseEntity<List<PuntuacionDTO>> getAllPuntuaciones() {
        List<PuntuacionDTO> puntuaciones = trivialService.getAllPuntuaciones();
        return ResponseEntity.ok(puntuaciones);
    }

    // Endpoint para guardar un nombre de usuario
    @PostMapping("/usuario/guardar")
    public ResponseEntity<Puntuacion> guardarNombreUsuario(@RequestParam String nombreUsuario) {
        Puntuacion puntuacion = trivialService.guardarNombreUsuario(nombreUsuario);
        return ResponseEntity.ok(puntuacion);
    }

    @PostMapping("/pregunta/contestar")
    public ResponseEntity<Boolean> contestarPregunta(@RequestBody Preguntas pregunta, @RequestParam String respuesta) {
        boolean esCorrecta = trivialService.contestarPregunta(pregunta, respuesta);
        return ResponseEntity.ok(esCorrecta);
    }
}