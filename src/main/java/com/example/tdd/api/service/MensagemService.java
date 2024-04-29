package com.example.tdd.api.service;

import com.example.tdd.api.model.Mensagem;
import java.util.UUID;

public interface MensagemService {

    Mensagem registrarMensagem(Mensagem mensagem);
    Mensagem buscarMensagem(UUID id);
    Mensagem alterarMensagem(Mensagem mensagem);
    Mensagem removerMensagem(UUID id);
}
