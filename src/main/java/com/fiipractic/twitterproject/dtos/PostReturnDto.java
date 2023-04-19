package com.fiipractic.twitterproject.dtos;

import com.fiipractic.twitterproject.entities.Like;

import java.util.List;
import java.util.UUID;

public record PostReturnDto(UUID id,
                            String username,
                            String message,
                            Long timestamp,
                            List<ReturnReplyDto> replyDto) {
}
