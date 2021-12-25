package com.springbootblog.restapi.service.impl;

import com.springbootblog.restapi.entity.Post;
import com.springbootblog.restapi.exception.ResourceNotFoundException;
import com.springbootblog.restapi.payload.PostDto;
import com.springbootblog.restapi.payload.PostResponse;
import com.springbootblog.restapi.repository.PostRepository;
import com.springbootblog.restapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert dto to entity
        Post post =  mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        //convert entity to dto
        PostDto postDto1 = mapToDTo(newPost);

        return postDto1;
    }
    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, sort);

        // get content for page object
        Page<Post> posts = postRepository.findAll(pageable);

        //get content for the page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content =  listOfPosts.stream().map(post -> mapToDTo(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return  postResponse;

    }

    @Override
    public PostDto getPostByID(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTo(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTo(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id", id));

        postRepository.delete(post);
    }

    //converted entity to dto
     private PostDto mapToDTo(Post post) {
         PostDto postDto = mapper.map(post, PostDto.class);

        /* PostDto postDto = new PostDto();
         postDto.setId(post.getId());
         postDto.setTitle(post.getTitle());
         postDto.setDescription(post.getDescription());
         postDto.setContent(post.getContent());
         */
         return postDto;
     }
    
     //converted dto to entity
     private Post mapToEntity(PostDto postDto){
         Post post = mapper.map(postDto, Post.class);

         /*Post post = new Post();
         post.setTitle(postDto.getTitle());
         post.setDescription(postDto.getDescription());
         post.setContent(postDto.getContent());*/
         return post;
     }
}
