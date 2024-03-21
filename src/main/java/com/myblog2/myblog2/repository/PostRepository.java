package com.myblog2.myblog2.repository;

import com.myblog2.myblog2.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
