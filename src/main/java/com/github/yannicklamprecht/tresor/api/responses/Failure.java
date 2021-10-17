package com.github.yannicklamprecht.tresor.api.responses;

import java.util.UUID;

public record Failure<T>(UUID passedUUID, String message)
        implements com.github.yannicklamprecht.tresor.api.responses.EconomyResponse<T> {
}
