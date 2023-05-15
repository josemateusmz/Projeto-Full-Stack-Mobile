package com.gofinancas.domain.exception;

public class TrataException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TrataException(String mesnagem){
        super(mesnagem);
    }
}
