package com.example.trivial_back.Repositorios;

import com.example.trivial_back.Modelos.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Long> {

    // Method que devuelve una categoría por su nombre
    Optional<Categorias> findByNombre(String nombre);
}