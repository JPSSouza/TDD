package com.example.tdd.api.repository;

import com.example.tdd.api.model.Mensagem;
import com.example.tdd.api.repository.MensagemRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MensagemRepositoryTest {

    @Mock
    private MensagemRepository mensagemRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirRegistrarMensagem(){
        var mensagem = criaMensagem();

        Mockito.when(mensagemRepository.save(ArgumentMatchers.any(Mensagem.class))).thenReturn(mensagem);

        var mensagemRecebida = mensagemRepository.save(mensagem);

        AssertionsForClassTypes.assertThat(mensagemRecebida)
                .isNotNull()
                .isEqualTo(mensagem);

        Mockito.verify(mensagemRepository, Mockito.times(1)).save(mensagem);
    }

    @Test
    void devePermitirConsultarMensagem (){

        var id = UUID.randomUUID();
        var msg = criaMensagem();

        msg.setId(id);

        Mockito.when(mensagemRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(msg));

        var msgRecebida = mensagemRepository.findById(id);

        AssertionsForClassTypes.assertThat(msgRecebida)
                .isPresent()
                .containsSame(msg);
        msgRecebida.ifPresent(msgRec -> {
            AssertionsForInterfaceTypes.assertThat(msgRec.getId()).isEqualTo(msg.getId());
            AssertionsForClassTypes.assertThat(msgRec.getConteudo()).isEqualTo(msg.getConteudo());
        });
        Mockito.verify(mensagemRepository, Mockito.times(1)).findById(ArgumentMatchers.any(UUID.class));

    }

    @Test
    void deveRemoverMensagem(){
        var id = UUID.randomUUID();
        Mockito.doNothing().when(mensagemRepository).deleteById(ArgumentMatchers.any(UUID.class));
        mensagemRepository.deleteById(id);

        Mockito.verify(mensagemRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any(UUID.class));
    }

    @Test
    void devePermitirListarMensagens(){
        var msg1 = criaMensagem();
        var msg2 = criaMensagem();
        var listaMsg = Arrays.asList(
                msg1,
                msg2);

        Mockito.when(mensagemRepository.findAll()).thenReturn(listaMsg);

        var msgRecebida = mensagemRepository.findAll();

        AssertionsForInterfaceTypes.assertThat(msgRecebida)
                .hasSize(2)
                .containsExactlyInAnyOrder(msg1, msg2);

        Mockito.verify(mensagemRepository, Mockito.times(1)).findAll();


    }

    private Mensagem criaMensagem(){
        return Mensagem.builder()

                .usuario("João Guilherme")
                .conteudo("conteúdo")
                .build();
    }
}
