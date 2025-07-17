package oi.curso.microservice.mscartoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oi.curso.microservice.mscartoes.entity.ClinteCartoes;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCartoesDTO {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static ClienteCartoesDTO fromModel(ClinteCartoes model){
        return new ClienteCartoesDTO(
                model.getCartoes().getNome(),
                model.getCartoes().getBandeira().toString(),
                model.getLimiteLiberado()
        );

    }

}