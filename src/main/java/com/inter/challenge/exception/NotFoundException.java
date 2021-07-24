package com.inter.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 15/07/2021
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
