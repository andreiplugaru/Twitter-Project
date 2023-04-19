package com.fiipractic.twitterproject.dtos;

import java.util.UUID;

public record ReturnReplyDto(UUID id, String username, String message, Long timestamp) {
}
