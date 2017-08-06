package com.n26.challenge.application;

import com.n26.challenge.domain.Transaction;
import com.n26.challenge.domain.resource.Statistic;

public interface TransactionService {
    void create(final Transaction transaction);
    Statistic getStatistic();
}
