package com.example.trivial_back.Controladores;

import com.example.trivial_back.DTO.CategoriaDTO;
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
    @PostMapping("/pregunta/crear/{categoriaId}")
    public ResponseEntity<Preguntas> createPregunta(@PathVariable Long categoriaId, @RequestBody PreguntasDTO preguntaDTO) {
        Preguntas nuevaPregunta = trivialService.createPregunta(categoriaId, preguntaDTO);
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
    public ResponseEntity<PreguntasDTO> getPreguntaAleatoria(@PathVariable String categoria) {
        PreguntasDTO preguntaDTO = trivialService.getPreguntaAleatoria(categoria);
        return ResponseEntity.ok(preguntaDTO);
    }

    // Endpoint para obtener una categoria aleatoria
    @GetMapping("/categoria/aleatoria")
    public ResponseEntity<CategoriaDTO> getCategoriaAleatoria() {
        CategoriaDTO categoriaDTO = trivialService.getCategoriaAleatoria();
        return ResponseEntity.ok(categoriaDTO);
    }

    // Endpoint para listar las puntuaciones
    @GetMapping("/puntuaciones")
    public ResponseEntity<List<PuntuacionDTO>> getAllPuntuaciones() {
        List<PuntuacionDTO> puntuaciones = trivialService.getAllPuntuaciones();
        return ResponseEntity.ok(puntuaciones);
    }

    // Endpoint para guardar un nombre de usuario
    @PostMapping("/usuario/guardar")
    public ResponseEntity<PuntuacionDTO> guardarNombreUsuario(@RequestParam String nombreUsuario) {
        PuntuacionDTO puntuacionDTO = trivialService.guardarNombreUsuario(nombreUsuario);
        return ResponseEntity.ok(puntuacionDTO);
    }

    // Endpoint para contestar una pregunta
    @PostMapping("/pregunta/contestar")
    public ResponseEntity<String> contestarPregunta(@RequestBody PreguntasDTO preguntaDTO, @RequestParam String respuesta, @RequestParam String nombreUsuario) {
        String resultado = trivialService.contestarPregunta(preguntaDTO, respuesta, nombreUsuario);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint para obtener el id de una pregunta por enunciado
    @GetMapping("/pregunta/id")
    public ResponseEntity<Long> getPreguntaIdByEnunciado(@RequestParam String enunciado) {
        Long preguntaId = trivialService.getPreguntaIdByEnunciado(enunciado);
        return ResponseEntity.ok(preguntaId);
    }
}