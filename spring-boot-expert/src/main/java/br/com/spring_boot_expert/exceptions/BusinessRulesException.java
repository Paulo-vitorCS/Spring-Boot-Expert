package br.com.spring_boot_expert.exceptions;

public class BusinessRulesException extends RuntimeException {

    public BusinessRulesException(String message) {
        super(message);
    }

}
