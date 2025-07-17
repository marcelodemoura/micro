package oi.curso.microservice.mscartoes.dto;

import lombok.Data;
import oi.curso.microservice.mscartoes.entity.BandeiraCartao;
import oi.curso.microservice.mscartoes.entity.Cartoes;

import java.math.BigDecimal;

@Data
public class CartoesDTO {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartoes toModel(){
        return new Cartoes(nome,bandeira,renda,limite);
    }

}
