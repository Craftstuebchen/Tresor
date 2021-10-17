package com.github.yannicklamprecht.tresor.impl;

import com.github.yannicklamprecht.tresor.api.Account;
import com.github.yannicklamprecht.tresor.api.Tresor;
import com.github.yannicklamprecht.tresor.api.responses.EconomyResponse;
import com.github.yannicklamprecht.tresor.api.responses.Failure;
import com.github.yannicklamprecht.tresor.api.responses.Success;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public record SampleTresor(Executor asyncExecutor, EconomyAdapter adapter) implements Tresor {

    @Override
    public CompletableFuture<EconomyResponse<Boolean>> hasAccount(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new Success<>(uuid, adapter.hasAccount(uuid));
            } catch (Exception e) {
                return new Failure<>(uuid, e.getMessage());
            }
        }, asyncExecutor);
    }

    @Override
    public CompletableFuture<EconomyResponse<Account>> getAccount(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new Success<>(uuid, adapter.getAccount(uuid));
            } catch (Exception e) {
                return new Failure<>(uuid, e.getMessage());
            }
        }, asyncExecutor);
    }
}
