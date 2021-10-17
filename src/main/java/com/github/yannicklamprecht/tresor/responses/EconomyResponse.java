package com.github.yannicklamprecht.tresor.responses;

import java.util.UUID;

public sealed interface EconomyResponse<T> permits Failure, Success {
    UUID passedUUID();
}
