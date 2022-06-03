package com.projetologistica.logisticaapi.controller;

import com.projetologistica.logisticaapi.domain.model.Cliente;
import com.projetologistica.logisticaapi.domain.repository.ClienteRepository;
import com.projetologistica.logisticaapi.domain.service.CatalogoClienteService;
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
@RequestMapping("/clientes")
@Api(value = "API REST Logistica")

public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    public ClienteController(ClienteRepository clienteRepository, CatalogoClienteService catalogoClienteService) {
        this.clienteRepository = clienteRepository;
        this.catalogoClienteService = catalogoClienteService;
    }

    // Listar todos os clientes
    @ApiOperation(value = "Retorna uma lista de Cliente")
    @GetMapping()
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Buscar apena por cliente expecifico
    @ApiOperation(value = "Busca um cliente expecifico")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    // Adiciona um cliente
    @ApiOperation(value = "Atualiza um cliente")
    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        //return clienteRepository.save(cliente);
        return catalogoClienteService.salvar(cliente);
    }

    // Atualizar cliente
    @PutMapping("/atualizar/{id}")
    @ApiOperation(value = "Adiciona um cliente expecifico")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();

        } else {
            cliente.setId(id);
            //cliente = clienteRepository.save(cliente);
            cliente = catalogoClienteService.salvar(cliente);
            return ResponseEntity.ok(cliente);
        }
    }

    // Deletar
    @DeleteMapping("/deletar/{id}")
    @ApiOperation(value = "Deleta um cliente expecifico")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            //clienteRepository.deleteById(id);
            catalogoClienteService.excluir(id);
            return ResponseEntity.noContent().build();
        }

    }
}
