package com.example.tdd.api.service;

import com.example.tdd.api.exception.MensagemNotFoundException;
import com.example.tdd.api.model.Mensagem;
import com.example.tdd.api.repository.MensagemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class MensagemServiceImpl implements MensagemService{
    @Autowired
    private final MensagemRepository repository;
    @Override
    public Mensagem registrarMensagem(Mensagem mensagem) {
        mensagem.setId(UUID.randomUUID());
        return repository.save(mensagem);
    }

    @Override
    public Mensagem buscarMensagem(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new MensagemNotFoundException("Mensagem não encontrada"));
    }

    @Override
    public Mensagem alterarMensagem(Mensagem mensagem) {
        return repository.save(mensagem);
    }

    @Override
    public Mensagem removerMensagem(UUID id) {
        return null;
    }
}
