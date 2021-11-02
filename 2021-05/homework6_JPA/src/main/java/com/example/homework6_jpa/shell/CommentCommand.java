package com.example.homework6_jpa.shell;

import com.example.homework6_jpa.domain.Author;
import com.example.homework6_jpa.domain.Book;
import com.example.homework6_jpa.domain.Comment;
import com.example.homework6_jpa.service.BookService;
import com.example.homework6_jpa.service.CommentService;
import com.example.homework6_jpa.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.util.Optional;

@ShellComponent
public class CommentCommand {
    private CommentService service;
    private BookService bookService;

    public CommentCommand(@Autowired CommentService commentService, @Autowired BookService bookService) {
        this.service = commentService;
        this.bookService = bookService;
    }

    @ShellMethod(value = "find comment by id", key = {"findCommentById"})
    public String findCommentById(@ShellOption long id){
        return service.getById(id).toString();
    }

    @ShellMethod(value = "find comment by book", key = {"findCommentsForBook"})
    public String findCommentByBook(@ShellOption long bookId){
        return service.getByBook(bookId).toString();
    }

    @ShellMethod(value = "get all comments", key = {"listComments"})
    public String findAllComments(){
        return Util.listToString(service.getAll());
    }

    @ShellMethod(value = "delete comment", key = {"deleteComment"})
    public String deleteComment(@ShellOption long id) {
        service.delete(id);
        return String.format("Comment %s is deleted", id);
    }

    @ShellMethod(value = "add comment", key = {"addComment"})
    public String addComment(@ShellOption long bookId, @ShellOption String text,
                             @ShellOption String user, @ShellOption LocalDateTime dateTime){
        Book book = bookService.get(bookId).orElseThrow();
        Comment comment = new Comment(book, text, user);
        service.save(comment);
        try {
            return String.format("Comment %s is added", comment.toString());
        } catch (EmptyResultDataAccessException exp) {
            return "There is no such author or genre " + exp.getRootCause();
        }
    }
}
