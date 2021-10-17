package com.github.yannicklamprecht.tresor.api;

import com.github.yannicklamprecht.tresor.api.responses.EconomyResponse;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Tresor {
    CompletableFuture<EconomyResponse<Boolean>> hasAccount(UUID uuid);
    CompletableFuture<EconomyResponse<Account>> getAccount(UUID uuid);
}
