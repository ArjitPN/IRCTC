package com.myblog2.myblog2.repository;

import com.myblog2.myblog2.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment>findByPostId(long postId);
}
