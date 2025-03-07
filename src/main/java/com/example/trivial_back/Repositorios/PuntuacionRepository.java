package com.example.trivial_back.Repositorios;

import com.example.trivial_back.DTO.PuntuacionDTO;
import com.example.trivial_back.Modelos.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {

    @Query("SELECT new com.example.trivial_back.DTO.PuntuacionDTO(p.fecha, p.nombreUsuario, p.aciertos, p.fallos, p.puntuacion) FROM Puntuacion p")
    List<PuntuacionDTO> findAllPuntuaciones();
}