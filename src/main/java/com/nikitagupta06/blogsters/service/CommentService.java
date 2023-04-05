package com.nikitagupta06.blogsters.service;

import com.nikitagupta06.blogsters.exception.ResourceNotFoundException;
import com.nikitagupta06.blogsters.model.Comment;
import com.nikitagupta06.blogsters.model.Post;
import com.nikitagupta06.blogsters.model.User;
import com.nikitagupta06.blogsters.repository.CommentRepository;
import com.nikitagupta06.blogsters.repository.PostRepository;
import com.nikitagupta06.blogsters.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public Comment addCommentOnPost(Long user_id, Long post_id, Comment comment)
            throws ResourceNotFoundException {
//        Optional<User> userOptional = userRepository.findById(user_id);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            List<Post> posts = user.getPosts();
//            Optional<Post> postOptional = posts.stream()
//                    .filter(p -> p.getId().equals(post_id))
//                    .findFirst();
//            if (postOptional.isPresent()) {
//                Post post = postOptional.get();
//                comment.setUser(user);
//                comment.setPost(post);
//                Comment savedComment = commentRepository.save(comment);
//                List<Comment> existing_comments_user = user.getComments();
//                existing_comments_user.add(savedComment);
//                user.setComments(existing_comments_user);
//                List<Comment> existing_comments_post = post.getComments();
//                existing_comments_post.add(savedComment);
//                post.setComments(existing_comments_post);
//                return savedComment;
//            } else {
//                throw new EntityNotFoundException("Post with ID " + post_id + " for userOptional ID " + user_id + " not found");
//            }
//        } else {
//            throw new EntityNotFoundException("User with ID " + user_id + " not found");
//        }
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + post_id));

        comment.setUser(user);
        comment.setPost(post);
        comment.setCreated_at(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long user_id, Long post_id, Long comment_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Comment> userComments = user.getComments();
            Optional<Comment> userCommentOptional = userComments.stream()
                    .filter(c1 -> c1.getId().equals(comment_id)).findFirst();
            userComments.remove(userCommentOptional.get());

            List<Post> posts = user.getPosts();
            Optional<Post> postOptional = posts.stream()
                    .filter(p -> p.getId().equals(post_id))
                    .findFirst();
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                List<Comment> postComments = post.getComments();
                Optional<Comment> postCommentOptional = postComments.stream()
                        .filter(c -> c.getId().equals(comment_id))
                        .findFirst();
                if (postCommentOptional.isPresent()) {
                    Comment comment = postCommentOptional.get();
                    postComments.remove(comment);
                    commentRepository.delete(comment);

                } else {
                    throw new EntityNotFoundException("Comment with ID " + comment_id + " for post id " + post_id + " and user ID " + user_id + " not found");
                }
            } else {
                throw new EntityNotFoundException("Post with ID " + post_id + " for user ID " + user_id + " not found");
            }
        } else {
            throw new EntityNotFoundException("User with ID " + user_id + " not found");
        }
    }

//    public List<Post> findAllPosts(Long user_id) {
//        Optional<User> user = userRepository.findById(user_id);
//        if (user.isPresent()) {
//            List<Post> posts = user.get().getPosts();
//            return posts;
//        } else {
//            throw new EntityNotFoundException("User with ID " + user_id + " not found");
//        }
//    }

    /*
     * CREATE
     * READ
     * UPDATE
     * DELETE
     * */
}
