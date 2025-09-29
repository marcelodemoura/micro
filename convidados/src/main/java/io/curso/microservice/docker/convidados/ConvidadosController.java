package io.curso.microservice.docker.convidados;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class ConvidadosController {


    private final ConvidadosRepository convidadosRepository;

    public ConvidadosController(ConvidadosRepository convidadosRepository) {
        this.convidadosRepository = convidadosRepository;
    }

    @GetMapping("getConvidados")
    public List<Convidado> getConvidados(){
        return convidadosRepository.findAll();
    }
}
