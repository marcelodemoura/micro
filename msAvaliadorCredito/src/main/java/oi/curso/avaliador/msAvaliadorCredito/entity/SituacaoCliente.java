package oi.curso.avaliador.msAvaliadorCredito.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoCliente {

    private DadosCliente cliente;
    private List<CartoesCliente> cartoes;


}
