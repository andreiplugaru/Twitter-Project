package com.fiipractic.twitterproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "posts")
public class BasePost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private User user;
    private String message;
    private Long timestamp;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parent")
    private List<Reply> replies;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
    private List<Like> likes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
    private List<Mention> mentions;

}
