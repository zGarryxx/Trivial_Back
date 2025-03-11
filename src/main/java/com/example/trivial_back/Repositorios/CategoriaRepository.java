package com.example.trivial_back.Repositorios;

import com.example.trivial_back.Modelos.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Long> {

}