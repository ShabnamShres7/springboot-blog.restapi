package com.springbootblog.restapi.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long id;

    //name should not be empty or null
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email should not be empty or null
    //email field validation
    @NotEmpty
    @Email(message = "Email should not be null or empty")
    private String email;

    //comment body should not be empty or null
    //comment body must be minimum 10 characters
    @NotEmpty
    @Size(message = "Comment body must be 10 characters")
    private String body;
}
