package com.engapp.BlogService.controller;

import com.engapp.BlogService.dto.request.BlogRequest;
import com.engapp.BlogService.dto.response.ApiStructResponse;
import com.engapp.BlogService.dto.response.BlogResponse;
import com.engapp.BlogService.mapper.BlogMapper;
import com.engapp.BlogService.pojo.Blog;
import com.engapp.BlogService.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value="/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogMapper blogMapper;

    @PostMapping(value="/create")
    public ApiStructResponse<BlogResponse> createBlog(@RequestBody BlogRequest blogRequest) {
        Blog blog = this.blogMapper.blogRequestToBlog(blogRequest);
        blog.setCreatedDate(Instant.now());
        blog.setUpdatedDate(Instant.now());

        Blog blogPersist = this.blogService.createBlog(blog);
        return ApiStructResponse.<BlogResponse>builder()
                .message("Create successful blog")
                .data(this.blogMapper.blogToBlogResponse(blogPersist))
                .build();
    }

    @GetMapping(value="/get-blogs")
    public ApiStructResponse<List<BlogResponse>> getBlogs() {

        List<Blog> blogs = this.blogService.getListBlog();

        List<BlogResponse> listBlogMapper = blogs
                .stream()
                .map(item -> this.blogMapper.blogToBlogResponse(item))
                .toList();

        return ApiStructResponse.<List<BlogResponse>>builder()
                .message("Get list blogs by pagination")
                .data(listBlogMapper)
                .build();
    }
}
