package com.fiipractic.twitterproject.dtos;

import java.util.List;
import java.util.UUID;

public record PostWithLikesDto(UUID id,
                               String username,
                               String message,
                               Long timestamp,
                               List<LikeDto> likes,
                               List<ReturnReplyDto> replies) {
}

