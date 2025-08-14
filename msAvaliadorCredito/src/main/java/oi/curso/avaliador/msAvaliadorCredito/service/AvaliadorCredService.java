package oi.curso.avaliador.msAvaliadorCredito.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import oi.curso.avaliador.msAvaliadorCredito.config.SolicEmisCardPublisher;
import oi.curso.avaliador.msAvaliadorCredito.entity.*;
import oi.curso.avaliador.msAvaliadorCredito.ex.DadosClienteException;
import oi.curso.avaliador.msAvaliadorCredito.ex.ComunicationMSException;
import oi.curso.avaliador.msAvaliadorCredito.ex.ErroSolicCartoesException;
import oi.curso.avaliador.msAvaliadorCredito.infra.CartoesClients;
import oi.curso.avaliador.msAvaliadorCredito.infra.ClienteClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCredService {

    private final ClienteClients clienteClient;
    private final CartoesClients cartoesClient;
    private final SolicEmisCardPublisher emisCardPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteException, ComunicationMSException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
            ResponseEntity<List<CartoesCliente>> cartoesClienteResponse = cartoesClient.getCartoesByClient(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesClienteResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteException();
            }
            throw new ComunicationMSException(e.getMessage(), status);

        }

    }

    public AvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws
            DadosClienteException, ComunicationMSException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesClient.getCartoesRendaAte(renda);


            List<Cartao> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovadoCartoes = cartoes.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
//                BigDecimal rendaDB = BigDecimal.valueOf(renda);
                BigDecimal idadeDB = BigDecimal.valueOf(dadosCliente.getIdade());

                var fator = idadeDB.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());


            return new AvaliacaoCliente(listaCartoesAprovadoCartoes);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteException();
            }
            throw new ComunicationMSException(e.getMessage(), status);

        }
    }

    public ProtocoloSolicCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartoes dados) {
        try {
            emisCardPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicCartao(protocolo);
        } catch (Exception e) {
            throw new ErroSolicCartoesException(e.getMessage());
        }
    }
}
