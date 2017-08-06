package com.n26.challenge;

import com.n26.challenge.domain.resource.Statistic;
import com.n26.challenge.repository.TransactionsRepository;
import com.n26.challenge.repository.impl.TransactionsRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static com.n26.challenge.utils.UtilsTest.createTransaction;
import static com.n26.challenge.utils.UtilsTest.getNow;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionsRepositoryTest {

    private TransactionsRepository repository;

    @Before
    public void setup() {
        repository = new TransactionsRepositoryImpl();
    }

    @Test
    public void shoulCleanOldTransactionsAndReturnProperlyStatistics() {

        repository.save(createTransaction(1000., getNow().minusSeconds(61)));
        repository.save(createTransaction(50., getNow().minusSeconds(10)));
        repository.save(createTransaction(20., getNow().minusSeconds(61)));
        repository.save(createTransaction(21., getNow().minusSeconds(50)));
        repository.save(createTransaction(40., getNow()));

        final Statistic statistic = repository.getStatistic();

        assertThat(statistic.getSum(), is(111.));
        assertThat(statistic.getAvg(), is(37.));
        assertThat(statistic.getMax(), is(50.));
        assertThat(statistic.getMin(), is(21.));
        assertThat(statistic.getCount(), is(3L));
    }
}
