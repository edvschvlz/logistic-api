package com.example.logisticapi.domain.service;

import com.example.logisticapi.domain.exception.NegocioException;
import com.example.logisticapi.domain.model.Cliente;
import com.example.logisticapi.domain.repository.ClienteRepository;
import com.example.logisticapi.domain.repository.EntregaRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private EntregaRespository entregaRespository;

    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream().anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        if (entregaRespository.findByCliente_Id(clienteId)) {
            throw new NegocioException("O cliente está associado a uma entrega!");
        }

        clienteRepository.deleteById(clienteId);
    }
}
