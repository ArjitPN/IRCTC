package com.myblog2.myblog2.controller;

import com.myblog2.myblog2.payload.PostDto;
import com.myblog2.myblog2.payload.PostResponse;
import com.myblog2.myblog2.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController//as we are developing an api application.if there is web application we can use @Controller
@RequestMapping("/api/posts")//by doing the url will be applicable for all the method of controller layer
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //the method shouldn't void bcoz it will return only http status code
    //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if (result.hasErrors()){//validation concept
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/posts/?pageNo=0&pageSize=10
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0",required = false)int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10",required = false)int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }
    //http://localhost:8080/api/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(

            @PathVariable(name="id")long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable (name="id") long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return ResponseEntity.ok(postResponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable (name="id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Delete Successfully...!",HttpStatus.OK);
    }
    //@PreAuthorize("hasRole('ADMIN')")->this annotation shiows that the below functionality can only access by admin

}
