package com.github.yannicklamprecht.tresor.api.responses;

import java.util.UUID;

public record Success<T>(UUID passedUUID, T value) implements com.github.yannicklamprecht.tresor.api.responses.EconomyResponse<T> {
}
