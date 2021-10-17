package com.github.yannicklamprecht.tresor;

import com.github.yannicklamprecht.tresor.responses.EconomyResponse;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Tresor {
    CompletableFuture<EconomyResponse<Boolean>> hasAccount(UUID uuid);
    CompletableFuture<EconomyResponse<Account>> getAccount(UUID uuid);
}
