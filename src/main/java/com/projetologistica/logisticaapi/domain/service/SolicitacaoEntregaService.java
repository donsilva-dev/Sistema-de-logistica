package com.projetologistica.logisticaapi.domain.service;

import com.projetologistica.logisticaapi.domain.model.Cliente;
import com.projetologistica.logisticaapi.domain.model.Entrega;
import com.projetologistica.logisticaapi.domain.model.StatusEntregas;
import com.projetologistica.logisticaapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;


@Service
public class SolicitacaoEntregaService {

    @Autowired
    private CatalogoClienteService catalogoClienteService;
    @Autowired
    private EntregaRepository entregaRepository;

    public SolicitacaoEntregaService(CatalogoClienteService catalogoClienteService, EntregaRepository entregaRepository) {
        this.catalogoClienteService = catalogoClienteService;
        this.entregaRepository = entregaRepository;
    }

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntregas.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);

    }
}
