package com.fiipractic.twitterproject.services;

import com.fiipractic.twitterproject.entities.Mention;
import com.fiipractic.twitterproject.entities.User;
import com.fiipractic.twitterproject.repositories.MentionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentionService {
    private final MentionRepository mentionRepository;

    public void save(List<Mention> mentions){
        mentionRepository.saveAll(mentions);
    }

    public List<Mention> getByUser(User user){
        return mentionRepository.getAllByUser(user);
    }
}
