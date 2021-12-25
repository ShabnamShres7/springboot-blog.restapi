package com.springbootblog.restapi.repository;

import com.springbootblog.restapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
