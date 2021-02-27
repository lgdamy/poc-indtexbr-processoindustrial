package com.indtexbr.processoindustrial.service;

import com.indtexbr.processoindustrial.domain.dto.ProposalDTO;
import com.indtexbr.processoindustrial.domain.model.Orcamento;
import com.indtexbr.processoindustrial.exception.FuntimeException;
import com.indtexbr.processoindustrial.repository.LicitacaoJpaRepository;
import com.indtexbr.processoindustrial.repository.OrcamentoJpaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lgdamy@raiadrogasil.com on 22/02/2021
 */
@Service
public class ProposalService {

    @Autowired
    private LicitacaoJpaRepository licitacaoRepo;

    @Autowired
    private OrcamentoJpaRepository orcamentoRepo;

    @Autowired
    private RabbitTemplate criaOrcamentoTemplate;

    private static final String SEM_ORCAMENTO = "N\u00e3o existe nenhuma proposta para esta licita\u00e7\u00e3o";

    @Transactional
    public Long create(ProposalDTO dto) {
        licitacaoRepo.findById(dto.getListing()).orElseThrow(() -> new FuntimeException("N\u00e3o existe licita\u00e7\u00e3o com este id", HttpStatus.NOT_FOUND));
        Orcamento o = orcamentoRepo.save(new Orcamento(dto));
        criaOrcamentoTemplate.convertAndSend(dto);
        return o.getId();
    }

    public List<ProposalDTO> findByListing(Long id) {
        var props = orcamentoRepo.findAllByLicitacaoNumero(id).stream().map(ProposalDTO::new).collect(Collectors.toList());
        if (props.isEmpty()) {
            throw new FuntimeException(SEM_ORCAMENTO, HttpStatus.NOT_FOUND);
        }
        return props;
    }

    public ProposalDTO findById(Long id) {
        return orcamentoRepo.findById(id)
                .map(ProposalDTO::new)
                .orElseThrow(() ->
                new FuntimeException(SEM_ORCAMENTO, HttpStatus.NOT_FOUND))
                ;
    }
}
