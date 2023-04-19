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
public class Reply extends BasePost {
    @ManyToOne
    private BasePost parent;
    private Boolean isPublic;
}
