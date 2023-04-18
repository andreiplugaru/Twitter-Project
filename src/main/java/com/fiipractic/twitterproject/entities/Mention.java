package com.fiipractic.twitterproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mentions")
public class Mention {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;

    public Mention(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
