package com.example.nnpiacv02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppUserException extends Exception {

    public AppUserException(String message) {
        super(message);
    }
}
