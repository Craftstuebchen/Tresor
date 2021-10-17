package com.github.yannicklamprecht.tresor.responses;

import java.util.UUID;

public record Success<T>(UUID passedUUID, T value) implements EconomyResponse<T> {
}
