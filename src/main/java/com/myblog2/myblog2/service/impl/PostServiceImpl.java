package com.myblog2.myblog2.service.impl;

import com.myblog2.myblog2.entities.Post;
import com.myblog2.myblog2.exception.ResourceNotFoundException;
import com.myblog2.myblog2.payload.PostDto;
import com.myblog2.myblog2.payload.PostResponse;
import com.myblog2.myblog2.repository.PostRepository;
import com.myblog2.myblog2.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;//bcoz repository layer helps us to do crud operation
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper=mapper;
    }
    //taking the dto object converting to entity because we cant save the dto object
    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }
    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
       Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);//this will give only those data which present in respective page no.
        Page<Post> content = postRepository.findAll(pageable);
        List<Post> posts = content.getContent();//converting the content back to posts
        List<PostDto> dto = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();

        postResponse.setContent(dto);
        postResponse.setPageNo(content.getNumber());
        postResponse.setPageSize(content.getSize());
        postResponse.setTotalPage(content.getTotalPages());
        postResponse.setTotalElements((int)content.getTotalElements());
        postResponse.setLast(content.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
        //using optional class
//        Optional<Post> byId = postRepository.findById(id);
//        if(byId.isPresent()){
//            postRepository.deleteById(id);
//        }else {
//            System.out.println("Record Not Found");
//        }
    }

    // convert Entity into DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }
    // convert DTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}
