package com.tsystems.javaschool.SBB.exception;

public class NoTicketsException extends RuntimeException{

    public NoTicketsException(){
    }

    public NoTicketsException(String message){
        super(message);
    }
    public NoTicketsException(String message, Throwable cause){
        super(message, cause);
    }
}
