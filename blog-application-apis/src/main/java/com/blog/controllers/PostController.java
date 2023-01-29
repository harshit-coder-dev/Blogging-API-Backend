package com.blog.controllers;

import com.blog.DTO.ApiResponse;
import com.blog.DTO.PostDto;
import com.blog.DTO.PostResponse;
import com.blog.config.BlogConstants;
import com.blog.repositories.PostRepo;
import com.blog.services.FileService;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")  // from application properties
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer uid, @PathVariable("categoryId") Integer cid) {

        PostDto createdPost = postService.createPost(postDto, uid, cid);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // get By User

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer uid) {
        List<PostDto> postDtos = postService.getPostByUser(uid);

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    // get By Category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId") Integer cid) {
        List<PostDto> postDtos = postService.getPostByCategory(cid);

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    // get All Post

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = BlogConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = BlogConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = BlogConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = BlogConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // get post By id

    @GetMapping("/posts/{pid}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("pid") Integer postId) {
        PostDto dtoList = postService.getPostById(postId);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    // delete post

    @DeleteMapping("posts/{postId}")
    public ApiResponse deletePost(@PathVariable("postId") Integer pid) {
        postService.deletePost(pid);

        return new ApiResponse("Post deleted SuccessFully ! ", true);
    }

    // updatePost

    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer pid) {
        PostDto post = postService.updatePost(postDto, pid);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("keyword") String key) {
        List<PostDto> postDtos = postService.searchPosts(key);

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    // post image upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                   @PathVariable("postId") Integer pid) throws IOException {
        PostDto postDto = postService.getPostById(pid);
        String fileName = fileService.uploadImage(path, image);

        postDto.setImageName(fileName);
        PostDto updatePost = postService.updatePost(postDto, pid);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    // method to serve files

    @GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void  downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource =fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
