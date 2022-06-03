package com.projetologistica.logisticaapi.domain.service;

import com.projetologistica.logisticaapi.domain.model.Entrega;
import com.projetologistica.logisticaapi.domain.model.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroOcorrenciaService {

    @Autowired
    private BuscarEntregaService buscarEntregaService;

    public RegistroOcorrenciaService(BuscarEntregaService buscarEntregaService) {
        this.buscarEntregaService = buscarEntregaService;
    }

    @Transactional
    public Ocorrencia registrar(Long id, String descricao) {
        Entrega entrega = buscarEntregaService.buscar(id);
         return entrega.adicionarOcorrencia (descricao);

    }
}
