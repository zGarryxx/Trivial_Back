package com.example.trivial_back.Repositorios;

import com.example.trivial_back.Modelos.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Long> {

    @Query("SELECT c FROM Categorias c ORDER BY RAND()")
    Categorias findRandomCategory();

    Optional<Categorias> findByNombre(String nombre);
}