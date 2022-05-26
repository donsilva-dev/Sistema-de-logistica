package com.projetologistica.logisticaapi.api.model;

import com.projetologistica.logisticaapi.domain.model.StatusEntregas;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class EntregaModel {

    private Long id;
    private String nomeCliente;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private StatusEntregas status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public DestinatarioModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(DestinatarioModel destinatario) {
        this.destinatario = destinatario;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public StatusEntregas getStatus() {
        return status;
    }

    public void setStatus(StatusEntregas status) {
        this.status = status;
    }

    public OffsetDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(OffsetDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public OffsetDateTime getDataFinalizada() {
        return dataFinalizada;
    }

    public void setDataFinalizada(OffsetDateTime dataFinalizada) {
        this.dataFinalizada = dataFinalizada;
    }
}
