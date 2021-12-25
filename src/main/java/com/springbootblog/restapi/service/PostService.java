package com.springbootblog.restapi.service;

import com.springbootblog.restapi.payload.PostDto;
import com.springbootblog.restapi.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
   PostDto createPost(PostDto postDto);

   PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);

   PostDto getPostByID(long id);

   PostDto updatePost(PostDto postDto,long id);

   void deletePostById(long id);

}
