package oi.curso.avaliador.msAvaliadorCredito.ex;

public class DadosClienteException extends  Exception{

    public DadosClienteException(){
        super("Dados do cliente não encontrados para o CPF informado");
    }

}
