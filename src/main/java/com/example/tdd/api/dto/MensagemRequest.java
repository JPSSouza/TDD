package com.example.tdd.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;

@Builder
@Data
@AllArgsConstructor
public class MensagemRequest {
    private String usuario;
    private String conteudo;
}
