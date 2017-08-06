package com.n26.challenge.repository;

import com.n26.challenge.domain.Transaction;
import com.n26.challenge.domain.resource.Statistic;

public interface TransactionsRepository {
    void save(final Transaction transaction);
    Statistic getStatistic();
}
