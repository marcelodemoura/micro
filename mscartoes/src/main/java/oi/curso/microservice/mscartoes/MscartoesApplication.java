package oi.curso.microservice.mscartoes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@Slf4j
public class MscartoesApplication {



	public static void main(String[] args) {
		log.info("Informação:{}", "teste info");
		log.info("Error:{}", "teste erro");
		log.info("Aviso:{}", "teste warn");
        SpringApplication.run(MscartoesApplication.class, args);
	}
}
