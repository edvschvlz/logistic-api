package com.example.logisticapi.domain.repository;

import com.example.logisticapi.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRespository extends JpaRepository<Entrega, Long> {
}
