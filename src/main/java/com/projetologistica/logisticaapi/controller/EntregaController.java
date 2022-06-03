package com.projetologistica.logisticaapi.controller;

import com.projetologistica.logisticaapi.api.assembler.EntregaAssembler;
import com.projetologistica.logisticaapi.domain.service.FinalizacaoEntregaService;
import com.projetologistica.logisticaapi.model.EntregaModel;
import com.projetologistica.logisticaapi.domain.model.Entrega;
import com.projetologistica.logisticaapi.domain.repository.EntregaRepository;
import com.projetologistica.logisticaapi.domain.service.SolicitacaoEntregaService;
import com.projetologistica.logisticaapi.model.input.EntregaInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private SolicitacaoEntregaService solicitacaoEntregaService;
    @Autowired
    private EntregaAssembler entregaAssembler;
    @Autowired
    private FinalizacaoEntregaService finalizacaoEntregaService;

    public EntregaController(EntregaRepository entregaRepository, SolicitacaoEntregaService solicitacaoEntregaService, EntregaAssembler entregaAssembler, FinalizacaoEntregaService finalizacaoEntregaService) {
        this.entregaRepository = entregaRepository;
        this.solicitacaoEntregaService = solicitacaoEntregaService;
        this.entregaAssembler = entregaAssembler;
        this.finalizacaoEntregaService = finalizacaoEntregaService;
    }

    @ApiOperation(value = "Solicita uma entrega")
    @PostMapping("/entregas")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {

        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);

        return entregaAssembler.toModel(entregaSolicitada);
    }

    @ApiOperation(value = "Finaliza uma entrega")
    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);

    }

    @GetMapping
    @ApiOperation(value = "Lista todas as entregas")
    public List<EntregaModel> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    @ApiOperation(value = "Lista entrega unica")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

}


