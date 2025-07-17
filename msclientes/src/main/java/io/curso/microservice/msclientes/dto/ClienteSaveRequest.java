package io.curso.microservice.msclientes.dto;

import io.curso.microservice.msclientes.entity.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ClienteSaveRequest {

    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toDTO() {
        return new Cliente(cpf,nome,idade);
    }
}
