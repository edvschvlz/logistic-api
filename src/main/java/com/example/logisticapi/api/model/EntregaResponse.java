package com.example.logisticapi.api.model;

import com.example.logisticapi.domain.model.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaResponse {
    private Long id;
    private ClienteResumoResponse cliente;
    private DestinatarioResponse destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private OffsetDateTime dataPedido;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private OffsetDateTime dataFinalizacao;
}
