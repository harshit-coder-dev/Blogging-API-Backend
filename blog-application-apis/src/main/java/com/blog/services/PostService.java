package com.blog.services;

import com.blog.DTO.PostDto;
import com.blog.DTO.PostResponse;
import com.blog.entities.Post;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto , Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);


    List<PostDto> searchPosts(String keyword);


}
