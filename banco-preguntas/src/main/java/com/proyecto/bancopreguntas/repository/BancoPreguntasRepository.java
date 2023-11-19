package com.proyecto.bancopreguntas.repository;

import com.proyecto.bancopreguntas.model.BancoPreguntas;
import com.proyecto.bancopreguntas.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoPreguntasRepository extends JpaRepository<BancoPreguntas, Long> {

    @Query("SELECT b FROM BancoPreguntas b WHERE b.idEmpresa = :idEmpresa")
    List<BancoPreguntas> findBancoPreguntasByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT b FROM BancoPreguntas b WHERE b.idEmpresa = :idEmpresa and b.id = :id ")
    BancoPreguntas findBancoPreguntasByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);

    @Query("SELECT b FROM BancoPreguntas b JOIN b.categoria c WHERE b.idEmpresa = :idEmpresa and c.id = :id")
    List<BancoPreguntas> findBancoPreguntasCategoriaByIdAndIdEmpresa(@Param("idEmpresa") Long idEmpresa, @Param("id") Long id);

    @Query("SELECT b FROM BancoPreguntas b WHERE b.idEmpresa = :idEmpresa and b.tipoBanco = :tipoBanco")
    List<BancoPreguntas> findBancoPreguntasByIdAndIdEmpresaAndTipoBanco(@Param("idEmpresa") Long idEmpresa, @Param("tipoBanco") String tipoBanco);

    BancoPreguntas findOneById(Long id);
}
