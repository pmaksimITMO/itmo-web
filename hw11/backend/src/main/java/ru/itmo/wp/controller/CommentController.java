package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.exception.NoSuchResourceException;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class CommentController {
    private final PostService postService;

    private final CommentService commentService;

    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("posts/{id}/comments")
    public void addComment(@PathVariable Long id,
            @RequestBody @Valid CommentForm commentForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Post post = postService.findById(id);
        if (post == null) {
            throw new NoSuchResourceException("Invalid post id");
        }
        postService.addComment(post, commentForm);
    }

    @GetMapping("posts/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post == null) {
            throw new NoSuchResourceException("Invalid post id");
        }
        return post.getComments();
    }

    @GetMapping("posts/{id}/comments/count")
    public long count(@PathVariable long id) {
        if (postService.findById(id) == null) {
            throw new NoSuchResourceException("Invalid post id");
        }
        return commentService.countByPostId(id);
    }

    @ExceptionHandler(NumberFormatException.class)
    public void handler() {
        throw new NoSuchResourceException("Invalid post id");
    }
}
