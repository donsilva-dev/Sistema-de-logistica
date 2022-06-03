package com.projetologistica.logisticaapi.domain.service;

import com.projetologistica.logisticaapi.domain.model.Entrega;
import com.projetologistica.logisticaapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private BuscarEntregaService buscarEntregaService;

    public FinalizacaoEntregaService(EntregaRepository entregaRepository, BuscarEntregaService buscarEntregaService) {
        this.entregaRepository = entregaRepository;
        this.buscarEntregaService = buscarEntregaService;
    }

    @Transactional
    public void finalizar(Long id) {
        Entrega entrega = buscarEntregaService.buscar(id);
        entrega.finalizar();

        entregaRepository.save(entrega);

    }
}
