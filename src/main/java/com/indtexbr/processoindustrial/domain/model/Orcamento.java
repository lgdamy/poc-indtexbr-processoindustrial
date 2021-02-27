package com.indtexbr.processoindustrial.domain.model;

import com.indtexbr.processoindustrial.domain.dto.ProposalDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author lgdamy@raiadrogasil.com on 22/02/2021
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Orcamento {

    @Id
    @GeneratedValue
    private Long id;

    private String empresa;

    private String cnpj;

    @ManyToOne
    private Licitacao licitacao;

    private BigDecimal valor;

    private Integer diasProducao;

    private Integer diasEntrega;

    private String urlDocumento;

    public Orcamento(ProposalDTO proposal) {
        this.empresa = proposal.getCompany();
        this.cnpj = proposal.getCompanyDocument();
        this.licitacao = new Licitacao(proposal.getListing());
        this.valor = proposal.getPrice();
        this.diasProducao = proposal.getProductionTime();
        this.diasEntrega = proposal.getShippingTime();
        this.urlDocumento = proposal.getProposalLink();
    }
}
