package com.proyectofinalg30.candidatos.repository;

import com.proyectofinalg30.candidatos.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Candidato findOneByEmail(String email);

    Candidato findOneByToken(String token);

    Candidato findOneById(Long id);
}
