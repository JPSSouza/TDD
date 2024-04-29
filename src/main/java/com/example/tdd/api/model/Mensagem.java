package com.example.tdd.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String conteudo;

    @Builder.Default
    private LocalDateTime dataCriacaoMensagem = LocalDateTime.now();

    @Builder.Default
    private int gostei = 0;
}
