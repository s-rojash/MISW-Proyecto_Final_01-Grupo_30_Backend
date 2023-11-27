package com.proyectofinalg30.candidatos.repository;

import com.proyectofinalg30.candidatos.model.CandidatoHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoHabilidadRepository extends JpaRepository<CandidatoHabilidad, Long> {
    List<CandidatoHabilidad> findOneByHabilidad_Id(Long id);

    @Query("SELECT ch FROM CandidatoHabilidad ch JOIN ch.habilidad h WHERE h.id in (:id)")
    List<CandidatoHabilidad> findOneByHabilidadBusqueda(List<Long> id);

    List<CandidatoHabilidad> findOneByCandidato_Id(Long id);

    CandidatoHabilidad findOneById(Long id);
}
