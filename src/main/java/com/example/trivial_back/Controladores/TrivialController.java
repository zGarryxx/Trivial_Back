package com.example.trivial_back.Controladores;

import com.example.trivial_back.DTO.*;
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
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/pregunta/crear")
    public ResponseEntity<Preguntas> createPregunta(@RequestBody CRUD_PreguntasDTO preguntaDTO) {
        Preguntas nuevaPregunta = trivialService.createPregunta(preguntaDTO);
        return ResponseEntity.ok(nuevaPregunta);
    }

    // Endpoint para obtener todas las preguntas
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/preguntas")
    public ResponseEntity<List<Preguntas>> getAllPreguntas() {
        List<Preguntas> preguntas = trivialService.getAllPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    // Endpoint para obtener una pregunta por id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pregunta/{id}")
    public ResponseEntity<Preguntas> getPreguntaById(@PathVariable Long id) {
        Optional<Preguntas> pregunta = trivialService.getPreguntaById(id);
        return pregunta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para actualizar una pregunta
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/pregunta/actualizar/{id}")
    public ResponseEntity<Preguntas> updatePregunta(@PathVariable Long id, @RequestBody CRUD_PreguntasDTO preguntaDTO) {
        Preguntas actualizadaPregunta = trivialService.updatePregunta(id, preguntaDTO);
        return ResponseEntity.ok(actualizadaPregunta);
    }

    // Endpoint para eliminar una pregunta
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/pregunta/eliminar/{id}")
    public ResponseEntity<Void> deletePregunta(@PathVariable Long id) {
        trivialService.deletePregunta(id);
        return ResponseEntity.ok().build();
    }

    // Endpoint para obtener una pregunta aleatoria de una categoria
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pregunta/aleatoria/{categoria}")
    public ResponseEntity<PreguntasDTO> getPreguntaAleatoria(@PathVariable String categoria) {
        PreguntasDTO preguntaDTO = trivialService.getPreguntaAleatoria(categoria);
        return ResponseEntity.ok(preguntaDTO);
    }

    // Endpoint para obtener una categoria aleatoria
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/categoria/aleatoria")
    public ResponseEntity<CategoriaDTO> getCategoriaAleatoria() {
        CategoriaDTO categoriaDTO = trivialService.getCategoriaAleatoria();
        return ResponseEntity.ok(categoriaDTO);
    }

    // Endpoint para listar las puntuaciones
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/puntuaciones")
    public ResponseEntity<List<PuntuacionDTO>> getAllPuntuaciones() {
        List<PuntuacionDTO> puntuaciones = trivialService.getAllPuntuaciones();
        return ResponseEntity.ok(puntuaciones);
    }

    // Endpoint para guardar un nombre de usuario
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/usuario/guardar")
    public ResponseEntity<PuntuacionDTO> guardarNombreUsuario(@RequestBody UsernameDTO nombreUsuario) {
        PuntuacionDTO puntuacionDTO = trivialService.guardarNombreUsuario(nombreUsuario);
        return ResponseEntity.ok(puntuacionDTO);
    }

    // Endpoint para contestar una pregunta
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/pregunta/contestar")
    public ResponseEntity<String> contestarPregunta(@RequestBody PreguntasDTO preguntaDTO, @RequestParam String respuesta, @RequestParam String nombreUsuario) {
        String resultado = trivialService.contestarPregunta(preguntaDTO, respuesta, nombreUsuario);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint para obtener el id de una pregunta por enunciado
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pregunta/id")
    public ResponseEntity<Long> getPreguntaIdByEnunciado(@RequestParam String enunciado) {
        Long preguntaId = trivialService.getPreguntaIdByEnunciado(enunciado);
        return ResponseEntity.ok(preguntaId);
    }

    // Endpoint para obtener todas las categorias
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/categorias")
    public ResponseEntity<List<Categorias>> getAllCategorias() {
        List<Categorias> categorias = trivialService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }
}