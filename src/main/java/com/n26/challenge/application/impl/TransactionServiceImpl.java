package com.n26.challenge.application.impl;

import com.n26.challenge.application.TransactionService;
import com.n26.challenge.domain.Transaction;
import com.n26.challenge.domain.resource.Statistic;
import com.n26.challenge.infrastructure.exception.TransactionOutOfTimeLimitException;
import com.n26.challenge.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionsRepository repository;

    @Autowired
    public TransactionServiceImpl(TransactionsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Transaction transaction) {
        final LocalDateTime timeLimit = Instant.now().atZone(UTC).minusSeconds(60).toLocalDateTime();

        if(transaction.getTimestamp().isBefore(timeLimit)) {
            throw  new TransactionOutOfTimeLimitException();
        }

        repository.save(transaction);
    }

    @Override
    public Statistic getStatistic() {
        return repository.getStatistic();
    }
}
