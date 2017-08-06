package com.n26.challenge.view;

import com.n26.challenge.domain.Transaction;
import com.n26.challenge.domain.resource.Statistic;

public interface TransactionEndpoint {
    void create(final Transaction transaction);
    Statistic getStatistic();
}