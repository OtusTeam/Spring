package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.dto.response.CommentBookResponseDto;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentBookResponseDto> getAllCommentsByBookId(long id) {
        return repository.getAllCommentsByBookId(id).stream().map(CommentBookResponseDto::toDto).toList();
    }
}
