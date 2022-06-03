package com.projetologistica.logisticaapi.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EntregaInput {

    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public ClienteIdInput getCliente() {
        return cliente;
    }

    public void setCliente(ClienteIdInput cliente) {
        this.cliente = cliente;
    }

    public DestinatarioInput getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(DestinatarioInput destinatario) {
        this.destinatario = destinatario;
    }
}
