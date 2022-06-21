package com.example.logisticapi.domain.service;

import com.example.logisticapi.domain.exception.NegocioException;
import com.example.logisticapi.domain.model.Cliente;
import com.example.logisticapi.domain.model.Entrega;
import com.example.logisticapi.domain.model.StatusEntrega;
import com.example.logisticapi.domain.repository.EntregaRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class EntregaService {

    private ClienteService clienteService;
    private EntregaRespository entregaRespository;

    public Entrega buscar(Long entregaId) {
        return entregaRespository.findById(entregaId).orElseThrow(() -> new NegocioException("Entrega não encontrada"));
    }

    @Transactional
    public Entrega salvar(Entrega entrega) {
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRespository.save(entrega);
    }

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = buscar(entregaId);

        entrega.finalizar();

        entregaRespository.save(entrega);
    }

    @Transactional
    public void cancelar(Long entregaId) {
        Entrega entrega = buscar(entregaId);

        entrega.cancelar();

        entregaRespository.save(entrega);
    }
}
