package com.nikitagupta06.blogsters.service;

import com.nikitagupta06.blogsters.model.Post;
import com.nikitagupta06.blogsters.model.User;
import com.nikitagupta06.blogsters.repository.PostRepository;
import com.nikitagupta06.blogsters.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    public Post createPost(Long user_id, Post post) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("User with ID " + user_id + " not found");
        }
        User user = userOptional.get();
        post.setUser(user);
//        post.setCreated_at(LocalDateTime.now());
        return postRepository.save(post);
    }

    public List<Post> findAllPosts(Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            List<Post> posts = user.get().getPosts();
            return posts;
        } else {
            throw new EntityNotFoundException("User with ID " + user_id + " not found");
        }
    }

    public Post findPostById(Long user_id, Long post_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            List<Post> posts = user.get().getPosts();
            Optional<Post> postOptional = posts.stream()
                    .filter(p -> p.getId().equals(post_id))
                    .findFirst();
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                return post;
            } else {
                throw new EntityNotFoundException("Post with ID " + post_id + " for user ID " + user_id + " not found");
            }
        } else {
            throw new EntityNotFoundException("User with ID " + user_id + " not found");
        }
    }

    public void deletePostById(Long user_id, Long post_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Post> posts = user.getPosts();
            Optional<Post> postOptional = posts.stream()
                    .filter(p -> p.getId().equals(post_id)).findFirst();
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                posts.remove(post);
                postRepository.delete(post);
            } else {
                throw new EntityNotFoundException("Post with ID " + post_id + " for user ID " + user_id + " not found");
            }
        } else {
            throw new EntityNotFoundException("User with ID " + user_id + " not found");
        }
    }

    public Post updatePost(Post post) {
        //TODO
        return postRepository.save(post);
    }


}
