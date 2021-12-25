package com.springbootblog.restapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

//Java bean class
@Data
public class PostDto {
    private long id;

    //title should not have empty or null
    //title should have at east 2 characters
    @NotEmpty
    @Size(min=2,message = "Post titles should have two characters")
    private String title;

    //post description should not have empty or null
    //post description should have at east 10 characters
    @NotEmpty
    @Size(min=10,message = "Post description should have 10 characters")
    private String description;


    //post content should not have empty or null
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
