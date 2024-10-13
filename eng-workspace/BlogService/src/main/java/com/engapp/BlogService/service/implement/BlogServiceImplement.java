package com.engapp.BlogService.service.implement;

import com.engapp.BlogService.configuration.CustomUserDetails;
import com.engapp.BlogService.configuration.PrincipalConfiguration;
import com.engapp.BlogService.exception.ApplicationException;
import com.engapp.BlogService.exception.ErrorCode;
import com.engapp.BlogService.pojo.Blog;
import com.engapp.BlogService.repository.BlogRepository;
import com.engapp.BlogService.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImplement implements BlogService {
    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog createBlog(Blog blog) {
        CustomUserDetails customUserDetails = this.principalConfiguration.getCustomUserDetails();

        blog.setUserId(customUserDetails.getId());
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getListBlog() {
        return this.blogRepository.findAll();
    }

    @Override
    public Blog getBlog(Integer id) {
        return this.blogRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    public void deleteBlogById(Integer id) {
        this.blogRepository.deleteById(id);
    }
}
