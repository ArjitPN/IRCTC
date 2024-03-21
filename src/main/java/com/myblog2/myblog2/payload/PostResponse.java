package com.myblog2.myblog2.payload;

import com.myblog2.myblog2.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPage;
    private boolean isLast;
}
