package com.blog.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {


    private Integer commentId;

    private String content;  // it is the comment of which post

}
