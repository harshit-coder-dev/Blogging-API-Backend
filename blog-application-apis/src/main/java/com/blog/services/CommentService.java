package com.blog.services;

import com.blog.DTO.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId); // create comment of which post

    void deleteComment(Integer commentId);
}
