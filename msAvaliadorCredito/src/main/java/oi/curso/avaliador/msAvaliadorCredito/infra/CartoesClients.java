package oi.curso.avaliador.msAvaliadorCredito.infra;

import oi.curso.avaliador.msAvaliadorCredito.entity.Cartao;
import oi.curso.avaliador.msAvaliadorCredito.entity.CartoesCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesClients {

    @GetMapping("cpf")
    ResponseEntity<List<CartoesCliente>> getCartoesByClient(@RequestParam("cpf") String cpf);

    @GetMapping("renda")
    ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);


}
