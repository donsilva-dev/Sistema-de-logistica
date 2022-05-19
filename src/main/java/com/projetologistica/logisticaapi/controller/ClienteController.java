package com.projetologistica.logisticaapi.controller;

import com.projetologistica.logisticaapi.domain.repository.ClienteRepository;
import com.projetologistica.logisticaapi.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    @GetMapping()
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Buscar apena por cliente expecifico
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    // Adiciona um cliente
    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualizar cliente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();

        } else {
            cliente.setId(id);
            cliente = clienteRepository.save(cliente);
            return ResponseEntity.ok(cliente);
        }
    }

    // Deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }
}
