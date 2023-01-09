package ru.otus.example.jpql_demo.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.jpql_demo.models.Category;

import java.util.List;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.StringUtils.hasLength;

@DisplayName("Репозиторий для Category должен")
@DataJpaTest
@Import(CategoryRepositoryJpa.class)
@Transactional(propagation = Propagation.NEVER)
class CategoryRepositoryJpaTest {

    @Autowired
    private CategoryRepositoryJpa categoryRepository;

    @DisplayName("возвращать список всех категорий")
    @Test
    void shouldFindAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories)
                .allMatch(category -> isNull(category.getParent()) || hasLength(category.getParent().getName()));
    }
}