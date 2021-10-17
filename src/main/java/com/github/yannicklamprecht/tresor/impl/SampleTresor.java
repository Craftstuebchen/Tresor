package com.github.yannicklamprecht.tresor.impl;

import com.github.yannicklamprecht.tresor.api.Account;
import com.github.yannicklamprecht.tresor.api.Tresor;
import com.github.yannicklamprecht.tresor.api.responses.EconomyResponse;
import com.github.yannicklamprecht.tresor.api.responses.Failure;
import com.github.yannicklamprecht.tresor.api.responses.Success;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

public record SampleTresor(Executor asyncExecutor) implements Tresor {

    @Override
    public CompletableFuture<EconomyResponse<Boolean>> hasAccount(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if(ThreadLocalRandom.current().nextBoolean()){
                    return new Success<>(uuid, ThreadLocalRandom.current().nextBoolean());
                } else {
                    throw new ExecutionException("", null);
                }

            }  catch (ExecutionException e) {
                return new Failure<>(uuid, "");
            }
        }, asyncExecutor);
    }

    @Override
    public CompletableFuture<EconomyResponse<Account>> getAccount(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if(ThreadLocalRandom.current().nextBoolean()){
                    return new Success<>(uuid, new Account(ThreadLocalRandom.current().nextBoolean(), 300.0));
                } else {
                    throw new ExecutionException("", null);
                }
            }  catch (ExecutionException e) {
                return new Failure<>(uuid, "");
            }
        }, asyncExecutor);
    }
}
