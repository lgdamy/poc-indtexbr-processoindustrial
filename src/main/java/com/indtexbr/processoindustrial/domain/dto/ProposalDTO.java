package com.indtexbr.processoindustrial.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.indtexbr.processoindustrial.domain.model.Licitacao;
import com.indtexbr.processoindustrial.domain.model.Orcamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author lgdamy@raiadrogasil.com on 22/02/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProposalDTO {

    private Long id;

    @NotNull
    private Long listing;

    @NotNull
    private String company;

    @NotNull
    private String companyDocument;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @NotNull
    private Integer productionTime;

    @NotNull
    private Integer shippingTime;

    @NotNull
    private String proposalLink;

    public ProposalDTO(Orcamento o) {
        super();
        this.id = o.getId();
        this.company = o.getEmpresa();
        this.companyDocument = o.getCnpj();
        this.listing = Optional.ofNullable(o.getLicitacao()).map(Licitacao::getNumero).orElse(0L);
        this.price = o.getValor();
        this.productionTime = o.getDiasProducao();
        this.shippingTime = o.getDiasEntrega();
        this.proposalLink = o.getUrlDocumento();
    }
}
