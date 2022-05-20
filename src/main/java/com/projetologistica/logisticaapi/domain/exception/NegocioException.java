package com.projetologistica.logisticaapi.domain.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String message){
        super(message);
    }
}
