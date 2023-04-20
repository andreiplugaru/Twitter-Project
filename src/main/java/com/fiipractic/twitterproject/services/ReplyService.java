package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.dtos.ReplyDto;
import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.Reply;
import com.fiipractic.twitterproject.exceptions.EntityNotFoundException;
import com.fiipractic.twitterproject.mappers.ReplyMapper;
import com.fiipractic.twitterproject.repositories.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyMapper replyMapper;
    private final ReplyRepository replyRepository;
    public void add(ReplyDto replyDto){
        Reply reply = replyMapper.mapToReply(replyDto);
        replyRepository.save(reply);
    }
    public Reply checkReplyExists(UUID postId){
        Optional<Reply> replyOptional = replyRepository.findById(postId);
        if(!replyOptional.isPresent())
            throw new EntityNotFoundException("Reply", postId.toString());
        return replyOptional.get();
    }

}
