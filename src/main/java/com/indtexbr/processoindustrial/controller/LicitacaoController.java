package com.indtexbr.processoindustrial.controller;

import com.indtexbr.processoindustrial.domain.dto.ListingDTO;
import com.indtexbr.processoindustrial.domain.dto.ResultPage;
import com.indtexbr.processoindustrial.domain.model.Licitacao;
import com.indtexbr.processoindustrial.exception.FuntimeException;
import com.indtexbr.processoindustrial.repository.LicitacaoJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Slf4j
@RestController
@RequestMapping("/listings/v1")
public class LicitacaoController {

    LicitacaoJpaRepository repository;

    private static final String NAO_ENCONTRADO = "Nenhuma licita\u00e7\u00e3o localizada";

    @Autowired
    public LicitacaoController(LicitacaoJpaRepository repository) {
        this.repository = repository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Long> gerarNova(@RequestBody @Valid @NotNull ListingDTO listing) {
        Long id = repository.save(new Licitacao(listing)).getNumero();
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListingDTO consultar(@PathVariable("id") Long id) {
        log.info("consultou o id {}", id);
        return new ListingDTO(
                repository.findById(id).orElseThrow(() ->
                new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultPage<ListingDTO> consultar(@RequestParam(value = "from")
                                            @DateTimeFormat(pattern = "ddMMyyyy") Date from,
                                            @RequestParam(value = "to")
                                            @DateTimeFormat(pattern = "ddMMyyyy") Date to,
                                            @RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Licitacao> licitacoes = repository.consultarPorDatas(from,to, PageRequest.of(page,size));
        if (!licitacoes.hasContent()) {
            throw new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
        }
        return new ResultPage<>(
                licitacoes.getContent()
                .stream().map(ListingDTO::new)
                .collect(Collectors.toList()),
                licitacoes.getPageable(),
                licitacoes.getTotalElements());
    }
}
