package com.fiipractic.twitterproject.dtos;

import java.util.UUID;

public record ReplyDto(UUID parentId, String message, Boolean isPublic) {
}
