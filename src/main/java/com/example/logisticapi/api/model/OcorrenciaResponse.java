package com.example.logisticapi.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaResponse {
    private Long id;

    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private OffsetDateTime dataRegistro;
}
