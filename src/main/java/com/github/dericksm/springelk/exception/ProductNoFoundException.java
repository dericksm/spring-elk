package com.github.dericksm.springelk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNoFoundException extends RuntimeException {

    public ProductNoFoundException(UUID id) {
        super("Product with id '%s' doesn't exist".formatted(id.toString()));
    }
}