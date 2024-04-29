package com.example.tdd.api.utils;

import com.example.tdd.api.dto.MensagemRequest;
import com.example.tdd.api.model.Mensagem;
import com.example.tdd.api.repository.MensagemRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class MensagemHelper {
    public static MensagemRequest gerarMensagemRequest() {
        return MensagemRequest.builder()
                .usuario("joe")
                .conteudo("xpto test")
                .build();
    }

    public static Mensagem gerarMensagem() {
        return Mensagem.builder()
                .usuario("joe")
                .conteudo("xpto test")
                .build();
    }

    public static Mensagem gerarMensagemCompleta() {
        var timestamp = LocalDateTime.now();
        return Mensagem.builder()
                .id(UUID.randomUUID())
                .usuario("joe")
                .conteudo("xpto test")
                .dataAlteracao(timestamp)
                .build();
    }

    public static Mensagem registrarMensagem(MensagemRepository repository) {
        var mensagem = gerarMensagem();
        mensagem.setId(UUID.randomUUID());
        return repository.save(mensagem);
    }
}
