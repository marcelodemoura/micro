package oi.curso.avaliador.msAvaliadorCredito.controller;

import lombok.RequiredArgsConstructor;
import oi.curso.avaliador.msAvaliadorCredito.entity.*;
import oi.curso.avaliador.msAvaliadorCredito.ex.DadosClienteException;
import oi.curso.avaliador.msAvaliadorCredito.ex.ComunicationMSException;
import oi.curso.avaliador.msAvaliadorCredito.ex.ErroSolicCartoesException;
import oi.curso.avaliador.msAvaliadorCredito.service.AvaliadorCredService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacao-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCredService avaliadorCredService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping("situacao-cliente")
    public ResponseEntity<?> consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCredService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);

        } catch (DadosClienteException e) {
            return ResponseEntity.notFound().build();

        } catch (ComunicationMSException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        try {
            AvaliacaoCliente avaliacaoCliente = avaliadorCredService
                    .realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(avaliacaoCliente);

        } catch (DadosClienteException e) {
            return ResponseEntity.notFound().build();

        } catch (ComunicationMSException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("solicitacoes-cartao")
    public ResponseEntity<?> solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartoes dadosSolicitacaoEmissaoCartoes) {
        try {
            ProtocoloSolicCartao protocolo = avaliadorCredService
                    .solicitarEmissaoCartao(dadosSolicitacaoEmissaoCartoes);
            return ResponseEntity.ok(protocolo);

        } catch (ErroSolicCartoesException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
