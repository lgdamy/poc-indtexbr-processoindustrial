package com.indtexbr.processoindustrial.repository;

import com.indtexbr.processoindustrial.domain.model.Licitacao;
import com.indtexbr.processoindustrial.domain.model.Orcamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
public interface OrcamentoJpaRepository extends JpaRepository<Orcamento,Long> {

    List<Orcamento> findAllByLicitacaoNumero(Long id);

}
