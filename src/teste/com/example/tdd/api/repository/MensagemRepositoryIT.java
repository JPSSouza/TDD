package com.example.tdd.api.repository;


import com.example.tdd.api.model.Mensagem;
import jakarta.transaction.Transactional;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class MensagemRepositoryIT {
    @Autowired
    MensagemRepository repository;

    @Nested
    class RepositoryMessagemIt{
        @Test
        void devePermitirCriarTabela(){
            long totalDeRegistros = repository.count();
            assertThat(totalDeRegistros).isNotNegative();
        }

        @Test
        void devePermitirRegistrarMensagem(){

            var id = UUID.randomUUID();
            var msg = criaMensagem();

            msg.setId(id);

            Mensagem msgRecebida = repository.save(msg);

            assertThat(msgRecebida)
                    .isInstanceOf(Mensagem.class)
                    .isNotNull();
            assertThat(msgRecebida.getId()).isEqualTo(id);
            assertThat(msgRecebida.getConteudo()).isEqualTo(msg.getConteudo());
            assertThat(msgRecebida.getUsuario()).isEqualTo(msg.getUsuario());
        }

        @Test
        void devePermitirConsultarMensagem (){
            var id = UUID.fromString("b2548bd8-20ec-480d-963f-76ce31dd680e");
            var conteudo = "Te amo filho";

            Optional<Mensagem> msgRecebida = repository.findById(id);

            assertThat(msgRecebida).isNotNull();
            msgRecebida.ifPresent(msgRec ->{
                assertThat(msgRec.getId()).isEqualTo(id);
                assertThat(msgRec.getConteudo()).isEqualTo(conteudo);
                assertThat(msgRec.getDataCriacao()).isNotNull();
            });

        }

        @Test
        void deveRemoverMensagem(){
            var id = UUID.fromString("6b6852ba-fb2e-4541-aed1-5f01d62297ec");

            repository.deleteById(id);
            var msgRecebida = repository.findById(id);

            assertThat(msgRecebida).isEmpty();

        }

        @Test
        void devePermitirListarMensagens(){
        }
    }
    private Mensagem criaMensagem(){
        return Mensagem.builder()
                .usuario("João Guilherme")
                .conteudo("conteúdo")
                .build();
    }


}
