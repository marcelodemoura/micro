package io.curso.microservice.msclientes.Controller;

import io.curso.microservice.msclientes.Service.ClienteService;
import io.curso.microservice.msclientes.dto.ClienteSaveRequest;
import io.curso.microservice.msclientes.entity.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public String status(){
        log.info("status do msclientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toDTO();
        service.save(cliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/cpf")
    public ResponseEntity<Cliente> dadosCliente(@RequestParam("cpf") String cpf) {
        return service.getByCPF(cpf)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
