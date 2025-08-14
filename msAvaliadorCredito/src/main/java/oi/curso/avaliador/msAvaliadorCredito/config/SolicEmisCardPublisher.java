package oi.curso.avaliador.msAvaliadorCredito.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import oi.curso.avaliador.msAvaliadorCredito.entity.DadosSolicitacaoEmissaoCartoes;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class SolicEmisCardPublisher {

    public final RabbitTemplate rabbitTemplate;
    public final Queue queueEmissaoCartoes;

    public void solicitarCartao(DadosSolicitacaoEmissaoCartoes dados) throws JsonProcessingException {
        var json = ConvetIntJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);

    }

    private String ConvetIntJson(DadosSolicitacaoEmissaoCartoes dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);
    }

}
