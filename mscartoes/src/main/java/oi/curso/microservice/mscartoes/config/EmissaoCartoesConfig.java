package oi.curso.microservice.mscartoes.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import oi.curso.microservice.mscartoes.entity.Cartoes;
import oi.curso.microservice.mscartoes.entity.ClinteCartoes;
import oi.curso.microservice.mscartoes.entity.DadosSolicitacaoEmissaoCartoes;
import oi.curso.microservice.mscartoes.repository.CartoesRepository;
import oi.curso.microservice.mscartoes.repository.ClienteCartoesRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartoesConfig {

    private final CartoesRepository cartoesRepository;
    private final ClienteCartoesRepository clienteCartoesRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void recSolicEmicao(@Payload String payload) throws JsonProcessingException {

        try {
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartoes dadosSolicitacaoEmissaoCartoes
                    = mapper.readValue(payload, DadosSolicitacaoEmissaoCartoes.class);
            Cartoes cartoes = cartoesRepository.findById(dadosSolicitacaoEmissaoCartoes.getIdCartao()).orElseThrow();
            ClinteCartoes clinteCartoes = new ClinteCartoes();
            clinteCartoes.setCartoes(cartoes);
            clinteCartoes.setCpf(dadosSolicitacaoEmissaoCartoes.getCpf());
            clinteCartoes.setLimiteLiberado(dadosSolicitacaoEmissaoCartoes.getLimiteLiberado());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

