package com.api.medical_scheduling.exception;

public class InvalidResourceException extends RuntimeException {
    public InvalidResourceException(String mensagem){ super(mensagem); }
}
