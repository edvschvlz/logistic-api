package com.example.logisticapi.domain.model;

import com.example.logisticapi.domain.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega")
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        this.getOcorrencias().add(ocorrencia);
    }

    public void finalizar() {
        if (!getStatus().equals(StatusEntrega.PENDENTE) || getStatus().equals(StatusEntrega.CANCELADA)) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }

        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public void cancelar() {
        if (getStatus().equals(StatusEntrega.FINALIZADA) || getStatus().equals(StatusEntrega.CANCELADA)) {
            throw new NegocioException("Entrega não pode ser cancelada");
        }

        setStatus(StatusEntrega.CANCELADA);
        setDataFinalizacao(OffsetDateTime.now());
    }
}
