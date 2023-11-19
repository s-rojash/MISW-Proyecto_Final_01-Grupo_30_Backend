package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findOneByNombre(String nombre);

    Categoria findOneById(Long id);
}
