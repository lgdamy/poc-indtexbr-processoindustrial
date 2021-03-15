package com.indtexbr.processoindustrial.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.indtexbr.processoindustrial.domain.model.Licitacao;
import com.indtexbr.processoindustrial.domain.model.Orcamento;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "199", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(example = "10")
    private Long listing;

    @NotNull
    @ApiModelProperty(example = "Tecidos Maia")
    private String company;

    @NotNull
    @ApiModelProperty(example = "30.281.367/0001-91")
    private String companyDocument;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @ApiModelProperty(example = "889912.99", notes = "R$")
    private BigDecimal price;

    @NotNull
    @ApiModelProperty(example = "12")
    private Integer productionTime;

    @ApiModelProperty(example = "5")
    @NotNull
    private Integer shippingTime;

    @NotNull
    @ApiModelProperty(example = "https://tecidofeito.com/assets/documento.pdf")
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
