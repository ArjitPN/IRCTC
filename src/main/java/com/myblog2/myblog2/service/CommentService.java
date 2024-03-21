package com.myblog2.myblog2.service;

import com.myblog2.myblog2.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId ,CommentDto commentDto);

    List<CommentDto> getCommentsByPostID(long postId);
    CommentDto getCommentById(long postId,long commentId);


    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deleteComment(long postId, long id);
}
