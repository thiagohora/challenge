package com.n26.challenge.repository.impl;

import com.n26.challenge.domain.Transaction;
import com.n26.challenge.domain.resource.Statistic;
import com.n26.challenge.repository.TransactionsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.DoubleStream;

import static java.time.ZoneOffset.UTC;

@Repository
public class TransactionsRepositoryImpl implements TransactionsRepository {

    private List<Transaction> transactions;
    private Statistic statistic;


    public TransactionsRepositoryImpl() {
        transactions = new LinkedList<>();
    }

    @Override
    public synchronized void save(Transaction transaction) {
        transactions.add(transaction);
        clean();
    }

    @Scheduled(fixedDelay = 59999)
    public synchronized void clean() {
        transactions.removeIf(transaction -> transaction.getTimestamp()
                .isBefore(Instant.now().atZone(UTC).minusSeconds(60).toLocalDateTime()));
        calcStatistics();
    }

    private void calcStatistics() {
        final Statistic.StatisticBuilder builder = Statistic.newBuilder();

        builder
                .setSum(getDoubleStream().sum())
                .setAvg(getDoubleStream().average().orElse(0))
                .setMax(getDoubleStream().max().orElse(0))
                .setMin(getDoubleStream().min().orElse(0))
                .setCount(transactions.stream().count())
                .setOldest(transactions.stream().min(Comparator.comparing(Transaction::getTimestamp))
                        .map(transaction -> transaction.getTimestamp()).orElse(null));

        statistic = builder.build();
    }

    @Override
    public synchronized Statistic getStatistic() {
        return statistic;
    }

    private DoubleStream getDoubleStream() {
        return transactions.stream().mapToDouble(Transaction::getAmount);
    }
}