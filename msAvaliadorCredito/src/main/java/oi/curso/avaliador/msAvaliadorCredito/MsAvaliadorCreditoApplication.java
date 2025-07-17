package oi.curso.avaliador.msAvaliadorCredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsAvaliadorCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAvaliadorCreditoApplication.class, args);
	}

}
