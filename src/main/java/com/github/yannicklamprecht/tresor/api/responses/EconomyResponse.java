package com.github.yannicklamprecht.tresor.api.responses;


import java.util.UUID;

public sealed interface EconomyResponse<T> permits Failure, Success {
    UUID passedUUID();
}
