package com.example.tdd.api.service;

import com.example.tdd.api.exception.MensagemNotFoundException;
import com.example.tdd.api.model.Mensagem;
import com.example.tdd.api.repository.MensagemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class MensagemServiceTest {

    private MensagemService service;
    @Mock
    private MensagemRepository repository;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        service = new MensagemServiceImpl(repository);
    }
    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }
    @Test
    void devePermitirResgistrarMensagem(){
        fail("teste não implementado");
    }
    @Test
    void devePermitirBuscarMensagem(){
        fail("teste não implementado");
    }
    @Test
    void deveLancarErroQdoIdMsgNaoforValidoParaBuscarMsg(){
        var id = UUID.randomUUID();
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()-> service.buscarMensagem(id))
                .isInstanceOf(MensagemNotFoundException.class)
                .hasMessage("Mensagem não encontrada");
        verify(repository, times(1)).findById(id);
    }
    @Test
    void devePermitirAlterarMensagem(){

        var id = UUID.fromString("b2548bd8-20ec-480d-963f-76ce31dd680e");
        var msgMock = criaMensagem();

        when(repository.findById(id)).thenReturn(Optional.of(msgMock));
        when(repository.save(msgMock)).thenReturn(msgMock);

        Optional<Mensagem> mensagem1 = repository.findById(id);

        mensagem1.ifPresent(msg->{
            assertThat(msg.getConteudo()).isEqualTo("Te amo filho");
            msg.setConteudo("teste");
            repository.save(msg);
        });

        var mensagem2 = repository.findById(id);

        mensagem2.ifPresent(msgRec->{
            assertThat(msgRec.getConteudo()).isEqualTo("teste");
        });
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(msgMock);

    }
    @Test
    void devePermitirRemoverMensagem(){
        fail("teste não implementado");
    }
    @Test
    void devePermitirListarMensagens(){
        fail("teste não implementado");
    }
    private Mensagem criaMensagem(){
        return Mensagem.builder()
                .usuario("João Guilherme")
                .conteudo("Te amo filho")
                .build();
    }
}
