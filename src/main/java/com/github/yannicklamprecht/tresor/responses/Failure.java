package com.github.yannicklamprecht.tresor.responses;

import java.util.UUID;

public record Failure<T>(String message, UUID passedUUID) implements EconomyResponse<T> {
}
