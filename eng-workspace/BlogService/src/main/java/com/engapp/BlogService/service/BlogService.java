package com.engapp.BlogService.service;

import com.engapp.BlogService.pojo.Blog;

import java.util.List;

public interface BlogService {

    public Blog createBlog(Blog blog);

    List<Blog> getListBlog();

    public Blog getBlog(Integer id);

    public void deleteBlogById(Integer id);
}
