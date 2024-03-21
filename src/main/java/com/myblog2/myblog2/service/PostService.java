package com.myblog2.myblog2.service;

import com.myblog2.myblog2.payload.PostDto;
import com.myblog2.myblog2.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto/*as dto object is coming from controller layer*/);

     PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

   PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}


