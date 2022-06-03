package com.projetologistica.logisticaapi.domain.service;

import com.projetologistica.logisticaapi.domain.exception.EntidadeNaoEncontradaException;
import com.projetologistica.logisticaapi.domain.model.Entrega;
import com.projetologistica.logisticaapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public BuscarEntregaService() {
    }

    public Entrega buscar(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
