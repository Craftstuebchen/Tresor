package com.github.yannicklamprecht.tresor.impl;

import com.github.yannicklamprecht.tresor.api.Account;

import java.util.UUID;

public interface EconomyAdapter {
    boolean hasAccount(UUID uuid) throws Exception;

    Account getAccount(UUID uuid) throws Exception;
}
