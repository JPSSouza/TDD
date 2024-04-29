package com.example.tdd.api.controller;


import com.example.tdd.api.model.Mensagem;
import com.example.tdd.api.service.MensagemService;
import com.example.tdd.api.utils.MensagemHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MensagemControllerTest {
    private MockMvc mockMvc;

    @Mock
    MensagemService service;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        MensagemController mensagemController = new MensagemController(service);
        mockMvc = MockMvcBuilders
                    .standaloneSetup(mensagemController)
                    .build();
    }
    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirRegistrarMensagem() throws Exception {
        var mensagemRequest = MensagemHelper.gerarMensagemRequest();

        when(service.registrarMensagem(any(Mensagem.class)))
                .thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/mensagens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mensagemRequest)))
                        .andExpect(status().isCreated());

        verify(service, times(1))
                .registrarMensagem(any(Mensagem.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
