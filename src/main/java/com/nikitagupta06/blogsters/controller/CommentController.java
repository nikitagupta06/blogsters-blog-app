package com.nikitagupta06.blogsters.controller;

import com.nikitagupta06.blogsters.exception.ResourceNotFoundException;
import com.nikitagupta06.blogsters.model.Comment;
import com.nikitagupta06.blogsters.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{user_id}/posts/{post_id}/comments")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> createCommentOnPost(@PathVariable Long user_id,
                                                       @PathVariable Long post_id, @RequestBody Comment comment)
            throws ResourceNotFoundException {

        return ResponseEntity.ok().body(commentService.addCommentOnPost(user_id, post_id, comment));
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Void> deletePostForUserById(@PathVariable Long user_id,
                                                      @PathVariable Long post_id,
                                                      @PathVariable Long comment_id) {
        commentService.deleteCommentById(user_id, post_id, comment_id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Comment> findAllCommentsForPost() {

        return ResponseEntity.ok().build();
    }

    /*
     * CREATE
     * READ
     * UPDATE
     * DELETE
     * */
}
