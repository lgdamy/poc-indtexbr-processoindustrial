package com.indtexbr.processoindustrial.domain.model;

import com.indtexbr.processoindustrial.domain.dto.ProposalDTO;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "10")
    private Long id;

    @ApiModelProperty(example = "Tecidos Carlinhos")
    private String empresa;

    @ApiModelProperty(example = "30.281.267/0002-91")
    private String cnpj;

    @ManyToOne
    private Licitacao licitacao;

    @ApiModelProperty(example = "2000.12")
    private BigDecimal valor;

    @ApiModelProperty(example = "10")
    private Integer diasProducao;

    @ApiModelProperty(example = "30")
    private Integer diasEntrega;

    @ApiModelProperty(example = "https://tecidofeito.com/assets/documento.pdf")
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
