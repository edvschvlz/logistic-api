package com.example.logisticapi.api.controller;

import com.example.logisticapi.api.mapper.OcorrenciaMapper;
import com.example.logisticapi.api.model.OcorrenciaResponse;
import com.example.logisticapi.api.model.input.OcorrenciaInput;
import com.example.logisticapi.domain.model.Ocorrencia;
import com.example.logisticapi.domain.service.EntregaService;
import com.example.logisticapi.domain.service.OcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private EntregaService entregaService;
    private OcorrenciaService ocorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponse registrarOcorrencia(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaMapper.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaResponse> listarOcorrencias(@PathVariable Long entregaId) {
        return ocorrenciaMapper.toCollectionModel(entregaService.buscar(entregaId).getOcorrencias());
    }
}
