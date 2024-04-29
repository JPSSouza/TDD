package com.example.tdd.api.controller;

import com.example.tdd.api.model.Mensagem;
import com.example.tdd.api.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
    private final MensagemService service;
    @Autowired
    public MensagemController(MensagemService service) {
        this.service = service;
    }
    @PostMapping()
    ResponseEntity<Mensagem> registrarMensagem(@RequestBody Mensagem mensagem){
        return new ResponseEntity<>(service.registrarMensagem(mensagem), HttpStatus.CREATED);
    }

}
