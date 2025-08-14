package oi.curso.microservice.mscartoes.config;

import org.springframework.amqp.core.Queue;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue emissaoCartoesQueue() {
        return new Queue("emissao-cartoes", true); // true = durável

    }

}

