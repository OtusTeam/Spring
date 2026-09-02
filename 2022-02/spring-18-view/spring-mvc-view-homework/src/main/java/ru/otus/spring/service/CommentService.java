package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.dto.response.CommentBookResponseDto;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    List<CommentBookResponseDto> getAllCommentsByBookId(long id);
}
