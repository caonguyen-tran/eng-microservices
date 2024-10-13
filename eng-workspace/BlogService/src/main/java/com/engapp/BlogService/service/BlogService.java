package com.engapp.BlogService.service;

import com.engapp.BlogService.pojo.Blog;

import java.util.List;

public interface BlogService {

    Blog createBlog(Blog blog);

    List<Blog> getListBlog();

    Blog getBlog(Integer id);

    void deleteBlogById(Integer id);
}
