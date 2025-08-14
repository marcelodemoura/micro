package oi.curso.avaliador.msAvaliadorCredito.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartoes {

    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;

}
