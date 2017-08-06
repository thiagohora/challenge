package com.n26.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.n26.challenge.infrastructure.jakcson.SerializerTimestamp;

import java.time.LocalDateTime;

public class Transaction {

    private Double amount;
    private LocalDateTime timestamp;

    @JsonCreator
    public Transaction(@JsonProperty("amount") Double amount,
                       @JsonDeserialize(using = SerializerTimestamp.class)
                       @JsonProperty("timestamp") LocalDateTime timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
