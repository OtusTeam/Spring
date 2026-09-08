package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.rest.dto.resposne.CommentBookResponseDto;
import ru.otus.spring.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<CommentBookResponseDto> getAllCommentsByBookId(long id) {
        return repository.getAllCommentsByBookId(id).stream().map(CommentBookResponseDto::toDto).toList();
    }
}
