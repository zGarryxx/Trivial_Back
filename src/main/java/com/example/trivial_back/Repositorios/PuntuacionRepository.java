package com.example.trivial_back.Repositorios;

import com.example.trivial_back.DTO.PuntuacionDTO;
import com.example.trivial_back.Modelos.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {

    // Method que devuelve una lista de puntuaciones
    @Query("SELECT new com.example.trivial_back.DTO.PuntuacionDTO(p.fecha, p.nombreUsuario, p.aciertos, p.fallos, p.puntuacion) FROM Puntuacion p")
    List<PuntuacionDTO> findAllPuntuaciones();

    // Method que devuelve si existe una puntuación por su nombre de usuario
    Optional<Puntuacion> findByNombreUsuario(String nombreUsuario);

}