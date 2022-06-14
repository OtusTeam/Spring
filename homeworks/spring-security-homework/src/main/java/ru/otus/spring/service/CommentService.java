package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.rest.dto.resposne.CommentBookResponseDto;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    List<CommentBookResponseDto> getAllCommentsByBookId(long id);
}
