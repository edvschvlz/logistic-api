package com.example.logisticapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaResponse {
    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
