package com.indtexbr.processoindustrial.domain.model;

import com.indtexbr.processoindustrial.domain.dto.ListingDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Licitacao implements Serializable {

    @Id
    @GeneratedValue
    private Long numero;

    private String categoria;

    private String grupo;

    private String cor;

    private String quantidade;

    @Temporal(TemporalType.TIMESTAMP)
    private Date criacao;

    @Temporal(TemporalType.DATE)
    private Date dataLimite;

    public Licitacao(ListingDTO dto) {
        super();
        this.categoria = dto.getCategory();
        this.grupo = dto.getGroup();
        this.cor = dto.getColor();
        this.quantidade = dto.getQuantity();
        this.criacao = new Date();
        this.dataLimite = dto.getDueTo();
    }
}
