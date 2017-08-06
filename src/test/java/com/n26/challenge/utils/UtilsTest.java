package com.n26.challenge.utils;

import com.n26.challenge.domain.Transaction;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

public class UtilsTest {

    public static LocalDateTime getNow() {
        return Instant.now().atZone(UTC).toLocalDateTime();
    }

    public static Transaction createTransaction(Double amount, LocalDateTime time) {
        return new Transaction(amount, time);
    }

}
