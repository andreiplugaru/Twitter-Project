package com.fiipractic.twitterproject.dtos;

import java.util.List;
import java.util.UUID;

public record ReturnReplyDto(UUID id,
                             String username,
                             String message,
                             Long timestamp,
                             List<ReturnReplyDto> replies) {
}
