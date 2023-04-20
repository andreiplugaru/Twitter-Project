package com.fiipractic.twitterproject.mappers;

import com.fiipractic.twitterproject.dtos.ReplyDto;
import com.fiipractic.twitterproject.dtos.ReturnReplyDto;
import com.fiipractic.twitterproject.entities.BasePost;
import com.fiipractic.twitterproject.entities.Reply;
import com.fiipractic.twitterproject.entities.User;
import com.fiipractic.twitterproject.services.BasePostService;
import com.fiipractic.twitterproject.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReplyMapper {
    private final BasePostService basePostService;
    public Reply mapToReply(ReplyDto replyDto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reply reply = new Reply();
        reply.setParent(basePostService.checkPostOrReplyExists(replyDto.parentId()));
        reply.setMessage(replyDto.message());
        reply.setIsPublic(replyDto.isPublic());
        reply.setTimestamp(System.currentTimeMillis());
        reply.setUser(user);
        return reply;
    }
}
