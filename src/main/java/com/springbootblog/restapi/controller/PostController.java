package com.springbootblog.restapi.controller;

import com.springbootblog.restapi.payload.PostDto;
import com.springbootblog.restapi.payload.PostResponse;
import com.springbootblog.restapi.service.PostService;
import com.springbootblog.restapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('Admin')")
    //create rest api for blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get for all post
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    )
    {
        return postService.getAllPosts(pageNo,pageSize, sortBy, sortDir);
    }

    //get post by Id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable (name="id") long id){
        return ResponseEntity.ok(postService.getPostByID(id));
    }

    //admin has permission to update the post
    @PreAuthorize("hasRole('Admin')")
    //update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable (name="id") long id){
        
        PostDto postResponse = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    //admin has permission to dlt the post
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePostById(@PathVariable (name = "id") long id){

        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);

    }

}
