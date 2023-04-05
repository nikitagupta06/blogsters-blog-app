package com.nikitagupta06.blogsters.controller;

import com.nikitagupta06.blogsters.model.Post;
import com.nikitagupta06.blogsters.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{user_id}/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPostForUser(@PathVariable Long user_id, @RequestBody Post post) {
        postService.createPost(user_id, post);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPostsForUser(@PathVariable Long user_id) {
        postService.findAllPosts(user_id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<Post> findPostByIdForUser(@PathVariable Long user_id, @PathVariable Long post_id) {
        Post post = postService.findPostById(user_id, post_id);
        return ResponseEntity.ok().body(post);
    }

    // TODO : Update post
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> updatePostForUser(@PathVariable Long user_id, @RequestBody Post post) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<Void> deletePostForUserById(@PathVariable Long user_id, @PathVariable Long post_id) {
        postService.deletePostById(user_id, post_id);
        return ResponseEntity.ok().build();
    }


}


