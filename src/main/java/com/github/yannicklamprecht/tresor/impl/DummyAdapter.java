package com.github.yannicklamprecht.tresor.impl;

import com.github.yannicklamprecht.tresor.api.Account;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class DummyAdapter implements EconomyAdapter {
    @Override
    public boolean hasAccount(UUID uuid) throws ExecutionException {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return ThreadLocalRandom.current().nextBoolean();
        } else {
            throw new ExecutionException("", null);
        }
    }

    @Override
    public Account getAccount(UUID uuid) throws ExecutionException {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return new Account(ThreadLocalRandom.current().nextBoolean(), 300.0);
        } else {
            throw new ExecutionException("", null);
        }
    }
}
