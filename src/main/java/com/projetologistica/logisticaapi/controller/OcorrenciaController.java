package com.projetologistica.logisticaapi.controller;

import com.projetologistica.logisticaapi.api.assembler.OcorrenciaAssembler;
import com.projetologistica.logisticaapi.domain.model.Entrega;
import com.projetologistica.logisticaapi.domain.model.Ocorrencia;
import com.projetologistica.logisticaapi.domain.model.OcorrenciaModel;
import com.projetologistica.logisticaapi.domain.service.BuscarEntregaService;
import com.projetologistica.logisticaapi.domain.service.RegistroOcorrenciaService;
import com.projetologistica.logisticaapi.model.input.OcorrenciaInput;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("entregas/{id}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;
    private BuscarEntregaService buscarEntregaService;

    public OcorrenciaController(RegistroOcorrenciaService registroOcorrenciaService, OcorrenciaAssembler ocorrenciaAssembler, BuscarEntregaService buscarEntregaService) {
        this.registroOcorrenciaService = registroOcorrenciaService;
        this.ocorrenciaAssembler = ocorrenciaAssembler;
        this.buscarEntregaService = buscarEntregaService;
    }

    @ApiOperation(value = "Registra uma ocorrencia")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long id,
                                     @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(id,ocorrenciaInput.getDescricao());
        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as ocorrencias")
    public List<OcorrenciaModel> listar(@PathVariable Long id) {
        Entrega entrega = buscarEntregaService.buscar(id);
        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
