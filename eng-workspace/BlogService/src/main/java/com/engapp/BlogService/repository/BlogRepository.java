package com.engapp.BlogService.repository;

import com.engapp.BlogService.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
