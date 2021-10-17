package com.github.yannicklamprecht.tresor;

import com.github.yannicklamprecht.tresor.api.Account;
import com.github.yannicklamprecht.tresor.api.Tresor;
import com.github.yannicklamprecht.tresor.api.responses.Failure;
import com.github.yannicklamprecht.tresor.api.responses.Success;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

class TresorTest {

    private static final Logger logger = LoggerFactory.getLogger(TresorTest.class);

    @Test
    void hasAccountReactive() {

        var tresor = Mockito.mock(Tresor.class);

        tresor.hasAccount(UUID.randomUUID()).whenComplete((booleanEconomyResponse, throwable) -> {
            switch (booleanEconomyResponse) {
                case Failure e -> System.out.println("Failed with " + e.message());
                case Success<Boolean> hasaccountresponse -> {
                    System.out.println("Account " + (hasaccountresponse.value() ? "present" : "absent" + "for uuid " + hasaccountresponse.passedUUID()));
                }
            }
        });
    }

    @Test
    void hasAccountBlocking() throws ExecutionException, InterruptedException {

        var tresor = Mockito.mock(Tresor.class);

        var response = tresor.hasAccount(UUID.randomUUID()).get();
        switch (response) {
            case Failure e -> System.out.println("Failed with " + e.message());
            case Success<Boolean> hasaccountresponse -> {
                System.out.println("Account " + (hasaccountresponse.value() ? "present" : "absent" + "for uuid " + hasaccountresponse.passedUUID()));
            }
        }
    }


    @Test
    void getAccountBlocking() throws ExecutionException, InterruptedException {

        var tresor = Mockito.mock(Tresor.class);

        var response = tresor.getAccount(UUID.randomUUID()).get();
        switch (response) {
            case Failure e -> System.out.println("Failed with " + e.message());
            case Success<Account> success -> {
                var account = success.value();
                logger.info("""
                                Active: {},
                                Overdraft limit: {}
                                """,
                        account.active(),
                        account.overdraftLimit()
                );
            }
        }

    }

}
