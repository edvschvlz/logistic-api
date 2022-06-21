package com.example.logisticapi.api.controller;

import com.example.logisticapi.api.mapper.EntregaMapper;
import com.example.logisticapi.api.model.EntregaResponse;
import com.example.logisticapi.api.model.input.EntregaInput;
import com.example.logisticapi.domain.model.Entrega;
import com.example.logisticapi.domain.repository.EntregaRespository;
import com.example.logisticapi.domain.service.EntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    private EntregaService entregaService;
    private EntregaRespository entregaRespository;
    private EntregaMapper entregaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaResponse criarEntrega(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaMapper.toEntity(entregaInput);
        Entrega entregaSolicitada = entregaService.salvar(novaEntrega);

        return entregaMapper.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaResponse> listarEntregas() {
        return entregaMapper.toCollectionModel(entregaRespository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaResponse> buscarEntrega(@PathVariable Long entregaId) {
        return entregaRespository.findById(entregaId).map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega))).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{entregaId}/finalizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        entregaService.finalizar(entregaId);
    }

    @PutMapping("/{entregaId}/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long entregaId) {
        entregaService.cancelar(entregaId);
    }
}
