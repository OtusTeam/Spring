package ru.otus.services.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Реестр опций меню ")
class MenuOptionsRegistryImplTest {

    private List<MenuOption> options;
    private MenuOptionsRegistryImpl menuOptionsRegistry;

    @BeforeEach
    void setUp() {
        options = List.of(new MenuOption(1, "opt1"), new MenuOption(2, "opt2"));
        menuOptionsRegistry = new MenuOptionsRegistryImpl(options);
    }

    @DisplayName("должен возвращать список ожидаемых опций ")
    @Test
    void shouldReturnExpectedAvailableMenuOptions() {
        var actualOptions = menuOptionsRegistry.getAvailableMenuOptions();
        assertThat(actualOptions)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(options);
    }

    // Обратить внимание на работу с Optional
    @DisplayName("должен корректно возвращать опцию меню по ее идентификатору ")
    @Test
    void shouldReturnExpectedMenuOptionById() {
        var expectedOption = options.get(0);
        var actualOption = menuOptionsRegistry.getMenuOptionById(expectedOption.getId());
        assertThat(actualOption).isNotEmpty()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedOption);
    }
}