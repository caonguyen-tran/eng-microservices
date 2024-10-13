package com.engapp.BlogService.mapper;

import com.engapp.BlogService.dto.request.BlogRequest;
import com.engapp.BlogService.dto.response.BlogResponse;
import com.engapp.BlogService.pojo.Blog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    BlogResponse blogToBlogResponse(Blog blog);

    Blog blogRequestToBlog(BlogRequest blogRequest);
}
