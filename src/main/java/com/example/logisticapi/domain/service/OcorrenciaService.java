package com.example.logisticapi.domain.service;

import com.example.logisticapi.domain.model.Entrega;
import com.example.logisticapi.domain.model.Ocorrencia;
import com.example.logisticapi.domain.repository.OcorrenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class OcorrenciaService {

    private EntregaService entregaService;
    private OcorrenciaRepository ocorrenciaRepository;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = entregaService.buscar(entregaId);

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(entrega);

        entrega.adicionarOcorrencia(ocorrencia);

        return ocorrenciaRepository.save(ocorrencia);
    }

}
