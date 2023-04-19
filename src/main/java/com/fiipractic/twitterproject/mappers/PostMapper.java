package com.fiipractic.twitterproject.mappers;

import com.fiipractic.twitterproject.dtos.PostCreationDto;
import com.fiipractic.twitterproject.dtos.PostReturnDto;
import com.fiipractic.twitterproject.dtos.PostWithLikesDto;
import com.fiipractic.twitterproject.dtos.ReturnReplyDto;
import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.Reply;
import com.fiipractic.twitterproject.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final LikeMapper likeMapper;

    public Post mapToPost(PostCreationDto postCreationDto) {
        Post post = new Post();
        post.setMessage(postCreationDto.message());
        post.setTimestamp(new Date().getTime());
        return post;
    }

    public PostReturnDto mapToPostDto(Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostReturnDto postReturnDto = new PostReturnDto(post.getId(),
                post.getUser().getUsername(),
                post.getMessage(),
                post.getTimestamp(),
                post.getReplies()
                        .stream()
                        .filter(reply -> reply.getIsPublic() || reply.getUser().equals(user))
                        .map(this::mapToReturnReplyDto)
                        .toList());
        return postReturnDto;
    }

    public ReturnReplyDto mapToReturnReplyDto(Reply reply) {
        ReturnReplyDto returnReplyDto = new ReturnReplyDto(
                reply.getId(),
                reply.getUser().getUsername(),
                reply.getMessage(),
                reply.getTimestamp());
        return returnReplyDto;
    }

    public PostWithLikesDto mapToPostWithLikesDto(Post post) {
        PostWithLikesDto postReturnDto = new PostWithLikesDto(post.getId(),
                post.getUser().getUsername(),
                post.getMessage(),
                post.getTimestamp(),
                post.getLikes()
                        .stream()
                        .map(likeMapper::mapToLikeDto).toList(),
                post.getReplies()
                        .stream()
                        .map(this::mapToReturnReplyDto).toList());
        return postReturnDto;
    }
}
