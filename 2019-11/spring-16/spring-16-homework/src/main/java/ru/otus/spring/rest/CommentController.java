package ru.otus.spring.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.model.Comment;
import ru.otus.spring.service.CommentService;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String listPage(Model model) {
        List<Comment> comments = commentService.getAll();
        model.addAttribute("comments", comments);
        return "commentList";
    }

}
