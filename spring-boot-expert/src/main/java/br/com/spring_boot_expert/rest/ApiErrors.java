package br.com.spring_boot_expert.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String errorMessage) {
        this.errors = Arrays.asList(errorMessage);
    }

}
