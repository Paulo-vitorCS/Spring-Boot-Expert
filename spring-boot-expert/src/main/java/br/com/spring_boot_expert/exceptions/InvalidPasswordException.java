package br.com.spring_boot_expert.exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("The password is not valid.");
    }
}
