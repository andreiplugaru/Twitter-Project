package com.fiipractic.twitterproject.mappers;

import com.fiipractic.twitterproject.dtos.LikeDto;
import com.fiipractic.twitterproject.entities.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {
    public LikeDto mapToLikeDto(Like like) {
        LikeDto likeDto = new LikeDto(like.getId(), like.getUser().getUsername());
        return likeDto;
    }
}
