package com.indtexbr.processoindustrial.repository;

import com.indtexbr.processoindustrial.domain.model.Licitacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
public interface LicitacaoJpaRepository extends JpaRepository<Licitacao,Long> {

    @Query("SELECT l FROM Licitacao l WHERE l.criacao BETWEEN :ini AND :fim OR l.dataLimite BETWEEN :ini AND :fim")
    Page<Licitacao> consultarPorDatas(@Param("ini") Date ini, @Param("fim") Date fim, Pageable p);
}
