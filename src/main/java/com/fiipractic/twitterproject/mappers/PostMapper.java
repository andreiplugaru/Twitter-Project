package com.fiipractic.twitterproject.mappers;

import com.fiipractic.twitterproject.dtos.PostCreationDto;
import com.fiipractic.twitterproject.dtos.PostReturnDto;
import com.fiipractic.twitterproject.entities.Post;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class PostMapper {
    public Post mapToPost(PostCreationDto postCreationDto) {
        Post post = new Post();
        post.setMessage(postCreationDto.message());
        post.setTimestamp(new Date().getTime());
        return post;
    }

    public PostReturnDto mapToPostDto(Post post) {
        PostReturnDto postReturnDto = new PostReturnDto(post.getMessage(), post.getTimestamp());
        return postReturnDto;
    }
}
