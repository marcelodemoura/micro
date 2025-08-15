package oi.curso.microservice.mscartoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oi.curso.microservice.mscartoes.dto.CartoesDTO;
import oi.curso.microservice.mscartoes.dto.ClienteCartoesDTO;
import oi.curso.microservice.mscartoes.entity.Cartoes;

import oi.curso.microservice.mscartoes.entity.ClinteCartoes;
import oi.curso.microservice.mscartoes.service.CartoesService;
import oi.curso.microservice.mscartoes.service.ClienteCartoesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor

public class CartoesController {



    private final CartoesService cartoesService;
    private final ClienteCartoesService clienteCartoesService;

    @GetMapping
    public String status() {
        log.info("Obtendo status ms Cartoes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<CartoesDTO> cadastrar(@RequestBody CartoesDTO cartoesDTO) {
        Cartoes cartoes = cartoesDTO.toModel();
        cartoesService.save(cartoes);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("renda")
    public ResponseEntity<List<Cartoes>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartoes> list = cartoesService.buscarPorRendaAte(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping("cpf")
    public ResponseEntity<List<ClienteCartoesDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<ClinteCartoes> lista = clienteCartoesService.buscarPorCpf(cpf);
        List<ClienteCartoesDTO> resultList = lista.stream()
                .map(ClienteCartoesDTO::fromModel)
                .toList();
        return ResponseEntity.ok(resultList);
    }

}
