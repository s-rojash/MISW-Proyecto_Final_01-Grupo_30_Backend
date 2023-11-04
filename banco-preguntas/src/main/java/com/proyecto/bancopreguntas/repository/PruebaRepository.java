package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {

    Prueba findOneById(Long id);

}
