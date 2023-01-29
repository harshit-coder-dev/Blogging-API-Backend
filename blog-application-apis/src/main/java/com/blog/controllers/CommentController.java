package com.blog.controllers;

import com.blog.DTO.ApiResponse;
import com.blog.DTO.CommentDto;
import com.blog.entities.Comment;
import com.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable("postId") Integer pid) {
        CommentDto commentDto = commentService.createComment(comment, pid);

        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment deleted Successfully", true), HttpStatus.OK);
    }
}
