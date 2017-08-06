package com.n26.challenge;

import com.n26.challenge.application.TransactionService;
import com.n26.challenge.application.impl.TransactionServiceImpl;
import com.n26.challenge.domain.Transaction;
import com.n26.challenge.infrastructure.exception.TransactionOutOfTimeLimitException;
import com.n26.challenge.repository.impl.TransactionsRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static com.n26.challenge.utils.UtilsTest.createTransaction;
import static com.n26.challenge.utils.UtilsTest.getNow;

public class TransactionServiceTest {

    private TransactionService service;

    @Before
    public void setup() {
        service = new TransactionServiceImpl(Mockito.mock(TransactionsRepositoryImpl.class));
    }


    @Test(expected = TransactionOutOfTimeLimitException.class)
    public void shouldThrowExceptionBecauseTransactionIsOutOfTimeLimit() {
        final LocalDateTime time = getNow().minusSeconds(61);
        final Transaction transaction = createTransaction(21.1, time);
        service.create(transaction);
    }

    @Test
    public void shouldCreateTheTransactionRegisterBecauseTimeIsNow() {
        final LocalDateTime time = getNow();
        final Transaction transaction = createTransaction(21.1, time);
        service.create(transaction);
    }

    @Test
    public void shouldCreateTheTransactionRegisterBecauseTimeIsNotOlderThan60S() {
        final LocalDateTime time = getNow().minusSeconds(55);
        final Transaction transaction = createTransaction(21.1, time);
        service.create(transaction);
    }


}
