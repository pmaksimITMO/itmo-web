package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long countByPostId(Long id) {
        return commentRepository.countByPostId(id);
    }
}
