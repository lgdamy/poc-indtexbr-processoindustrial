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
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Slf4j
@RestController
@RequestMapping("/api/listings")
public class LicitacaoController {

    LicitacaoJpaRepository repository;

    @Autowired
    public LicitacaoController(LicitacaoJpaRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Long> gerarNova(@RequestBody @Valid ListingDTO listing, HttpRequest request) {
        Long id = repository.save(new Licitacao(listing)).getNumero();
        return ResponseEntity.created(
                UriComponentsBuilder.fromHttpRequest(request)
                        .pathSegment("api","listing","v1",""+id)
                        .build().toUri()
        ).body(id);
    }

    @GetMapping(value = "/v1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListingDTO consultar(@PathVariable("id") Long id) {
        return new ListingDTO(
                repository.findById(id).orElseThrow(() ->
                new FuntimeException("Nenhuma licitação localizada", HttpStatus.NOT_FOUND)));
    }

    @GetMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultPage<ListingDTO> consultar(@PathVariable(value = "from")
                                            @DateTimeFormat(pattern = "ddMMyyyy") Date from,
                                            @PathVariable(value = "to")
                                            @DateTimeFormat(pattern = "ddMMyyyy") Date to,
                                            @PathVariable(value = "page") int page,
                                            @PathVariable(value = "size") int size) {
        Page<Licitacao> licitacoes = repository.consultarPorDatas(from,to, PageRequest.of(page,size));
        if (!licitacoes.hasContent()) {
            throw new FuntimeException("Nenhuma licitação localizada", HttpStatus.NOT_FOUND);
        }
        return new ResultPage<>(
                licitacoes.getContent()
                .stream().map(ListingDTO::new)
                .collect(Collectors.toList()),
                licitacoes.getPageable(),
                licitacoes.getTotalElements());
    }

}
