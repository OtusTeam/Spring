package ru.otus.example.conditionalandprofilesdemo.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.example.conditionalandprofilesdemo.model.base.Friend;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//@ActiveProfiles({"Oleg"})
//@ActiveProfiles({"Peter"})
//@ActiveProfiles({"Oleg", "Peter"})
@DisplayName("FriendsMap должна")
@SpringBootTest
class ConditionalAndProfilesDemoApplicationTests {

    @Autowired
    private Map<String, Friend> friendsMap;


    @DisplayName(" содержать Олега")
    @Test
    void shouldContainOleg() {
        assertThat(friendsMap).containsKey("oleg");
    }

    @DisplayName(" содержать Петра")
    @Test
    void shouldContainPetr() {
        assertThat(friendsMap).containsKey("peter");
    }

}
