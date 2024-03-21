package com.myblog2.myblog2.controller;

import com.myblog2.myblog2.payload.CommentDto;
import com.myblog2.myblog2.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId")long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsById(@PathVariable(value="postId")long postId){
     return commentService.getCommentsByPostID(postId);
    }
    //http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value="postId")long postId, @PathVariable(value="id")long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
    return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/{postID}/comments/{id}
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId")long postId,
                                                    @PathVariable("id")long id,
                                                    @RequestBody CommentDto commentDto){
     CommentDto dto =commentService.updateComment(postId,id,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/{postID}/comments/{id}
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId")long postId,
                                                @PathVariable("id") long id){
        commentService.deleteComment(postId,id);
        return new ResponseEntity<>("Comment Deleted successfully",HttpStatus.OK);
    }
}
