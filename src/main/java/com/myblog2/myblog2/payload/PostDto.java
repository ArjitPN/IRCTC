package com.myblog2.myblog2.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotEmpty//Spring validation
    @Size(min = 2,message = "Post title should hava at least 2 character")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post Description should have at least 10 character")
    private String description;
    private String content;
}
