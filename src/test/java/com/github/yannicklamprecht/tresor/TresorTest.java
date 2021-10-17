package com.github.yannicklamprecht.tresor;

import com.github.yannicklamprecht.tresor.api.Account;
import com.github.yannicklamprecht.tresor.api.responses.Failure;
import com.github.yannicklamprecht.tresor.api.responses.Success;
import com.github.yannicklamprecht.tresor.impl.SampleTresor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

class TresorTest {

    private static final Logger logger = LoggerFactory.getLogger(TresorTest.class);

    @Test
    void hasAccountReactive() {

        var tresor = new SampleTresor(Runnable::run);

        tresor.hasAccount(UUID.randomUUID()).whenComplete((response, throwable) -> {

            if (response instanceof Failure failure) {
                System.out.println("Failed with " + failure.message());
            } else if (response instanceof Success<Boolean> success) {
                System.out.println("Account " + (success.value() ? "present" : "absent" + "for uuid " + success.passedUUID()));
            }
        });
    }

    @Test
    void hasAccountBlocking() throws ExecutionException, InterruptedException {

        var tresor = new SampleTresor(Runnable::run);

        var response = tresor.hasAccount(UUID.randomUUID()).get();
        if (response instanceof Failure failure) {
            System.out.println("Failed with " + failure.message());
        } else if (response instanceof Success<Boolean> success) {
            System.out.println("Account " + (success.value() ? "present" : "absent" + "for uuid " + success.passedUUID()));
        }
    }


    @Test
    void getAccountBlocking() throws ExecutionException, InterruptedException {

        var tresor = new SampleTresor(Runnable::run);

        var response = tresor.getAccount(UUID.randomUUID()).get();

        if (response instanceof Failure failure) {
            System.out.println("Failed with " + failure.message());
        } else if (response instanceof Success<Account> success) {
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
