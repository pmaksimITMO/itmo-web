package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public PostService(PostRepository postRepository, UserRepository userRepository, JwtService jwtService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public void post(PostForm postForm) {
        Post post = new Post();
        User user = jwtService.find(postForm.getJwt());
        user.addPost(post);
        post.setTitle(postForm.getTitle());
        post.setText(postForm.getText());
        postRepository.save(post);
        userRepository.save(user);
    }

    public void addComment(Post post, CommentForm commentForm) {
        User user = jwtService.find(commentForm.getJwt());
        Comment comment = new Comment();
        comment.setText(commentForm.getText());
        comment.setUser(user);
        post.addComment(comment);
        postRepository.save(post);
    }
}
