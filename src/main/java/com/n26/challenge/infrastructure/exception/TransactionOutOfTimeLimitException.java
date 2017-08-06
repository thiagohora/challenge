package com.n26.challenge.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class TransactionOutOfTimeLimitException extends RuntimeException {

    public TransactionOutOfTimeLimitException() {
        super("transaction out of the time limit");
    }
}
