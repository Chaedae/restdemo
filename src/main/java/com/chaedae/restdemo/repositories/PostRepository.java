package com.chaedae.restdemo.repositories;

import com.chaedae.restdemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByPostNo(long postNo);
}
