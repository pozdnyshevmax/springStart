package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Set<Post> findAllByAutor(String autor);
    public Post findByTitle (String title);
    public List<Post> findAll();

}
