package com.example.security.repository;


import com.example.security.model.Blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<Blog,Long> {
    Page<Blog> findAll(Pageable pageable);

    @Query("SELECT b FROM Blog b WHERE b.name LIKE %:name%")
    List<Blog> findByName(@Param("name") String name);

}
