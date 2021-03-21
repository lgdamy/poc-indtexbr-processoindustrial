package com.indtexbr.processoindustrial.controller;

import com.indtexbr.processoindustrial.domain.dto.ProposalDTO;
import com.indtexbr.processoindustrial.service.ProposalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "Or\u00e7amento")
@RequestMapping("/proposals/v1")
public class OrcamentoController {

    private final ProposalService service;

    @ApiOperation(value = "Gera um orçamento novo")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long gerarNova(@RequestBody @Valid @NotNull ProposalDTO proposal) {
        return service.create(proposal);
    }

    @ApiOperation(value = "Consulta os detalhes de um or\u00e7amento pelo identificador")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProposalDTO consultar(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @ApiOperation(value = "Consulta todos or\u00e7amentos de uma determinada licita\u00e7\u00e3o")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProposalDTO> consultarPelasLicitacoes(@RequestParam("listing") Long listing) {
        return service.findByListing(listing);
    }
}
