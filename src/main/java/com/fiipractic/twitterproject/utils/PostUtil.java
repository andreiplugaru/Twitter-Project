package com.fiipractic.twitterproject.utils;

import com.fiipractic.twitterproject.entities.Post;
import com.fiipractic.twitterproject.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostUtil {
    public static List<String> getPotentialUsernames(Post post) {
        String[] tokens = post.getMessage().split("[ !.?]+");
        List<String> usernames = new ArrayList<>();
        for(String token : tokens)
            if(token.startsWith("@") && token.length() > 1)
                usernames.add(token.substring(1));
        return usernames;
    }

    public static List<User> getPostMentions(Post post, List<User> users) {
        List<String> usernames = getPotentialUsernames(post);
        List<User> mentions = users.stream()
                .filter(user -> usernames.contains(user.getUsername()))
                .toList();
        return mentions;
    }
}
