package oi.curso.avaliador.msAvaliadorCredito.controller;

import oi.curso.avaliador.msAvaliadorCredito.entity.AvaliacaoCliente;
import oi.curso.avaliador.msAvaliadorCredito.entity.DadosAvaliacao;
import oi.curso.avaliador.msAvaliadorCredito.entity.SituacaoCliente;
import oi.curso.avaliador.msAvaliadorCredito.ex.DadosClienteException;
import oi.curso.avaliador.msAvaliadorCredito.ex.ComunicationMSException;
import oi.curso.avaliador.msAvaliadorCredito.service.AvaliadorCredService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacao-credito")
public class AvaliadorCreditoController {

    AvaliadorCredService avaliadorCredService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping("situacao-cliente")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {

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
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {

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

}
