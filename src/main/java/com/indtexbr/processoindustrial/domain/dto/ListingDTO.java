package com.indtexbr.processoindustrial.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.indtexbr.processoindustrial.domain.model.Licitacao;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListingDTO {
    @ApiModelProperty(example = "10", readOnly = true)
    private Long id;
    @ApiModelProperty(example = "Tecido")
    @NotNull
    private String category;
    @ApiModelProperty(example = "Feltro")
    @NotNull
    private String group;
    @ApiModelProperty(example = "Cinza")
    @NotNull
    private String color;
    @ApiModelProperty(example = "40m")
    @NotNull
    private String quantity;
    @ApiModelProperty(example = "15/02/2021")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date createdAt;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3")
    @ApiModelProperty(example = "15/04/2021")
    private Date dueTo;

    @ApiModelProperty(example = "4", readOnly = true)
    private Integer proposals;

    public ListingDTO(Licitacao lic) {
        super();
        this.id = lic.getNumero();
        this.category = lic.getCategoria();
        this.group = lic.getGrupo();
        this.color = lic.getCor();
        this.quantity = lic.getQuantidade();
        this.createdAt = lic.getCriacao();
        this.dueTo = lic.getDataLimite();
        this.proposals = lic.getOrcamentos().size();
    }
}
