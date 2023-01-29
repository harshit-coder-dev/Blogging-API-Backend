package com.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String content;  // it is the comment of which post
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    private User user;


}
