package com.indtexbr.processoindustrial.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.indtexbr.processoindustrial.domain.model.Licitacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.TimeZone;

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
    private Long id;
    @NotNull
    private String category;
    @NotNull
    private String group;
    @NotNull
    private String color;
    @NotNull
    private String quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY", timezone = "GMT-3")
    private Date createdAt;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY", timezone = "GMT-3")
    private Date dueTo;

    public ListingDTO(Licitacao lic) {
        super();
        this.id = lic.getNumero();
        this.category = lic.getCategoria();
        this.group = lic.getGrupo();
        this.color = lic.getCor();
        this.quantity = lic.getQuantidade();
        this.createdAt = lic.getCriacao();
        this.dueTo = lic.getDataLimite();
    }
}
