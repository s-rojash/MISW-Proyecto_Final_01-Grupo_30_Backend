package com.proyecto.presentarprueba.repository;

import com.proyecto.presentarprueba.model.PresentarPrueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentarPruebaRepository extends JpaRepository<PresentarPrueba, Long> {

    @Query("SELECT pp FROM PresentarPrueba pp WHERE pp.id = :id and pp.idCandidato = :idCandidato")
    PresentarPrueba findPresntarPruebaById(@Param("id") Long id, @Param("idCandidato") Long idCandidato);
    @Query("SELECT pp FROM PresentarPrueba pp WHERE pp.idCandidato = :idCandidato and pp.idPrueba = :idPrueba")
    List<PresentarPrueba> findPresntarPruebaByIdCandidatoAndIdPrueba(@Param("idPrueba") Long idPrueba, @Param("idCandidato") Long idCandidato);

}
