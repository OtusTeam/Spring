package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.resposne.CommentBookResponseDto;
import ru.otus.spring.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @GetMapping("/{bookId}")
    public List<CommentBookResponseDto> getAllCommentsByBookId(@PathVariable("bookId") long bookId) {
        return commentService.getAllCommentsByBookId(bookId);
    }
}
