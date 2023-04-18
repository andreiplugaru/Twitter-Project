package com.fiipractic.twitterproject.dtos;

import java.util.UUID;

public record PostReturnDto(UUID id, String message, Long timestamp) {
}
