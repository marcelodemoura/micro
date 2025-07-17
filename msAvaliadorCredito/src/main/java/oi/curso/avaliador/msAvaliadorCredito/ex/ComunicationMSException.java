package oi.curso.avaliador.msAvaliadorCredito.ex;

import lombok.Getter;

public class ComunicationMSException extends  Exception{

     @Getter
     private Integer status;

    public ComunicationMSException(String msg, int status){
        super(msg);
        this.status = status;
    }

}
