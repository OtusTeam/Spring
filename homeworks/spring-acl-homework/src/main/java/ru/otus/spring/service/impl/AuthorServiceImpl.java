package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.rest.dto.resposne.AuthorBookResponseDto;
import ru.otus.spring.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author saveIfNotExists(Author author) {
        return repository.findByName(author.getName()).orElseGet(() -> repository.save(new Author(author.getName())));
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<AuthorBookResponseDto> getAllAuthorsByBookId(long id) {
        return repository.getAllAuthorsByBookId(id).stream().map(AuthorBookResponseDto::toDto).toList();
    }
}
