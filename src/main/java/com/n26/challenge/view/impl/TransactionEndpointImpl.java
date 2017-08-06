package com.n26.challenge.view.impl;

import com.n26.challenge.application.TransactionService;
import com.n26.challenge.domain.Transaction;
import com.n26.challenge.domain.resource.Statistic;
import com.n26.challenge.view.TransactionEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class TransactionEndpointImpl implements TransactionEndpoint {

    private TransactionService service;

    @Autowired
    public TransactionEndpointImpl(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/transactions")
    @ResponseStatus(CREATED)
    @Override
    public void create(@RequestBody Transaction transaction) {
        service.create(transaction);
    }

    @GetMapping("/statistics")
    @Override
    public Statistic getStatistic() {
        return service.getStatistic();
    }
}
