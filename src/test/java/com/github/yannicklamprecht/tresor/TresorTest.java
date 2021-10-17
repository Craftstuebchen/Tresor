package com.github.yannicklamprecht.tresor;

import com.github.yannicklamprecht.tresor.api.Account;
import com.github.yannicklamprecht.tresor.api.Tresor;
import com.github.yannicklamprecht.tresor.api.responses.Failure;
import com.github.yannicklamprecht.tresor.api.responses.Success;
import com.github.yannicklamprecht.tresor.impl.EconomyAdapter;
import com.github.yannicklamprecht.tresor.impl.SampleTresor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

class TresorTest {

    private static final Logger logger = LoggerFactory.getLogger(TresorTest.class);

    private EconomyAdapter adapter;

    private final UUID uuid = UUID.randomUUID();

    private Exception exception;
    private Tresor tresor;

    @BeforeEach
    void setup() {
        this.adapter = Mockito.mock(EconomyAdapter.class);
        this.tresor = new SampleTresor(Runnable::run, adapter);
        this.exception = new Exception("failed");
    }

    @Test
    void hasAccountReactiveSucceed() throws Exception {

        Mockito.doReturn(true).when(adapter).hasAccount(Mockito.eq(uuid));

        tresor.hasAccount(uuid).whenComplete((response, throwable) -> {

            Assertions.assertTrue(response instanceof Success);

            Success<Boolean> success = (Success<Boolean>) response;

            Assertions.assertTrue(success.value());
        });
    }

    @Test
    void hasAccountReactiveFail() throws Exception {

        Mockito.doThrow(exception).when(adapter).hasAccount(Mockito.eq(uuid));

        tresor.hasAccount(uuid).whenComplete((response, throwable) -> {

            Assertions.assertTrue(response instanceof Failure);
            Failure<Boolean> failure = (Failure<Boolean>) response;
            Assertions.assertEquals("failed", failure.message());
            Assertions.assertEquals(uuid, failure.passedUUID());
        });
    }

    @Test
    void hasAccountBlockingSucceed() throws Exception {

        Mockito.doReturn(true).when(adapter).hasAccount(Mockito.eq(uuid));

        var response = tresor.hasAccount(uuid).get();

        Assertions.assertTrue(response instanceof Success);

        Success<Boolean> success = (Success<Boolean>) response;

        Assertions.assertTrue(success.value());

    }

    @Test
    void hasAccountBlockingFail() throws Exception {

        Mockito.doThrow(exception).when(adapter).hasAccount(Mockito.eq(uuid));

        var response = tresor.hasAccount(uuid).get();

        Assertions.assertTrue(response instanceof Failure);
        Failure<Boolean> failure = (Failure<Boolean>) response;
        Assertions.assertEquals("failed", failure.message());
        Assertions.assertEquals(uuid, failure.passedUUID());

    }


    @Test
    void getAccountBlockingShouldSucceed() throws Exception {

        var expectedAccount = new Account(true, 300.0);

        Mockito.doReturn(expectedAccount).when(adapter).getAccount(Mockito.eq(uuid));

        var response = tresor.getAccount(uuid).get();

        Assertions.assertEquals(uuid, response.passedUUID());

        Assertions.assertTrue(response instanceof Success);

        Success<Account> success = (Success<Account>) response;

        var account = success.value();

        Assertions.assertEquals(expectedAccount.active(), account.active());
        Assertions.assertEquals(expectedAccount.overdraftLimit(), account.overdraftLimit());
    }

    @Test
    void getAccountBlockingShouldFail() throws Exception {

        Mockito.doThrow(exception).when(adapter).getAccount(Mockito.eq(uuid));

        var response = tresor.getAccount(uuid).get();

        Assertions.assertEquals(uuid, response.passedUUID());

        Assertions.assertTrue(response instanceof Failure);

        Failure<Account> failure = (Failure<Account>) response;

        Assertions.assertEquals("failed", failure.message());
    }


    @Test
    void getAccountReactiveShouldSucceed() throws Exception {

        var expectedAccount = new Account(true, 300.0);

        Mockito.doReturn(expectedAccount).when(adapter).getAccount(Mockito.eq(uuid));

        tresor.getAccount(uuid).whenComplete((response, throwable) -> {
            Assertions.assertEquals(uuid, response.passedUUID());

            Assertions.assertTrue(response instanceof Success);

            Success<Account> success = (Success<Account>) response;

            var account = success.value();

            Assertions.assertEquals(expectedAccount.active(), account.active());
            Assertions.assertEquals(expectedAccount.overdraftLimit(), account.overdraftLimit());
        });
    }

    @Test
    void getAccountReactiveShouldFail() throws Exception {

        Mockito.doThrow(exception).when(adapter).getAccount(Mockito.eq(uuid));

        tresor.getAccount(uuid).whenComplete((response, throwable) -> {
            Assertions.assertEquals(uuid, response.passedUUID());

            Assertions.assertTrue(response instanceof Failure);

            Failure<Account> failure = (Failure<Account>) response;

            Assertions.assertEquals("failed", failure.message());
        });
    }




    @Test
    void hasAccountReactiveSucceedSwitch() throws Exception {

        Mockito.doReturn(true).when(adapter).hasAccount(Mockito.eq(uuid));

        tresor.hasAccount(uuid).whenComplete((response, throwable) -> {
            Success<Boolean> successResult = null;
            Failure<Boolean> failureResult = null;
            switch (response) {
                case Success<Boolean> success -> {
                    successResult = success;
                }
                case Failure<Boolean> failure -> {
                    failureResult = failure;
                }
            }

            Assertions.assertNotNull(successResult);
            Assertions.assertNull(failureResult);

            Assertions.assertTrue(response instanceof Success);

            Success<Boolean> success = (Success<Boolean>) response;

            Assertions.assertTrue(success.value());
        });
    }

    @Test
    void hasAccountReactiveFailSwitch() throws Exception {

        Mockito.doThrow(exception).when(adapter).hasAccount(Mockito.eq(uuid));

        tresor.hasAccount(uuid).whenComplete((response, throwable) -> {

            Success<Boolean> successResult = null;
            Failure<Boolean> failureResult = null;
            switch (response) {
                case Success<Boolean> success -> {
                    successResult = success;
                }
                case Failure<Boolean> failure -> {
                    failureResult = failure;
                }
            }

            Assertions.assertNull(successResult);
            Assertions.assertNotNull(failureResult);

            Assertions.assertTrue(response instanceof Failure);
            Failure<Boolean> failure = (Failure<Boolean>) response;
            Assertions.assertEquals("failed", failure.message());
            Assertions.assertEquals(uuid, failure.passedUUID());
        });
    }


}
