package com.example.controller;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.repositoy.CommentRepository;
import com.example.repositoy.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public CommentController(CommentRepository commentRepository , PostRepository postRepository){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }

    @PostMapping()
    public ResponseEntity<Comment> createComment(
            @RequestBody Comment comment,
            @RequestParam long postId){
        Post post = postRepository.findById(postId).get(); // get() method is from optional class
        comment.setPost(post);  // here we are setting the Post Object in comment this will not store the entire post table in Db
        // instead it will only store foreign key that is post_id in comment table.
        return new ResponseEntity<>(commentRepository.save(comment) , HttpStatus.CREATED);
    }




}
