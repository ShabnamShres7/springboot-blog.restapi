package com.springbootblog.restapi.service;

import com.springbootblog.restapi.exception.BlogAPIException;
import com.springbootblog.restapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById (long postId, long commentId) throws BlogAPIException;

    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, Long commentId);


}
