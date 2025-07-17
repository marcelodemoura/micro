package oi.curso.avaliador.msAvaliadorCredito.infra;

import oi.curso.avaliador.msAvaliadorCredito.entity.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteClients {

    @GetMapping
    ResponseEntity<DadosCliente> dadosCliente(@RequestParam String cpf);
}
