package com.cloud.SubwayChat.repository;

import com.cloud.SubwayChat.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Post p WHERE p.id = :id")
    boolean existsById(Long id);
}
