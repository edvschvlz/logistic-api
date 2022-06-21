package com.example.logisticapi.api.controller;

import com.example.logisticapi.api.mapper.ClienteMapper;
import com.example.logisticapi.api.model.ClienteResponse;
import com.example.logisticapi.api.model.input.ClienteInput;
import com.example.logisticapi.domain.model.Cliente;
import com.example.logisticapi.domain.repository.ClienteRepository;
import com.example.logisticapi.domain.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private ClienteService clienteService;
    private ClienteMapper clienteMapper;

    @GetMapping
    public List<ClienteResponse> listarClientes() {
        return clienteMapper.toCollectionModel(clienteRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> buscarCliente(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId).map(cliente -> ResponseEntity.ok(clienteMapper.toModel(cliente))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse adicionarCliente(@Valid @RequestBody ClienteInput clienteInput) {
        Cliente novoCliente = clienteMapper.toEntity(clienteInput);
        Cliente clienteSolicitado = clienteService.salvar(novoCliente);

        return clienteMapper.toModel(clienteSolicitado);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable Long clienteId, @Valid @RequestBody ClienteInput clienteInput) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        clienteInput.setId(clienteId);
        Cliente novoCliente = clienteMapper.toEntity(clienteInput);
        Cliente clienteSolicitado = clienteService.salvar(novoCliente);

        return ResponseEntity.ok(clienteMapper.toModel(clienteSolicitado));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        clienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }
}
