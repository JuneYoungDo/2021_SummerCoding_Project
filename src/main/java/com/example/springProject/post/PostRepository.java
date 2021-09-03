package com.example.springProject.post;

import com.example.springProject.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {

    boolean existsById(Long postId);

}
