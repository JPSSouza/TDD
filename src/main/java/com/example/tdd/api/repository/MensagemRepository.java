package com.example.tdd.api.repository;

import com.example.tdd.api.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {
}
