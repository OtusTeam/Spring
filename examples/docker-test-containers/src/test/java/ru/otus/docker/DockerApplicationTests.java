package ru.otus.docker;

import com.mongodb.MongoClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DockerApplicationTests {
    private MongoTemplate mongoTemplate;
    @Container
    public GenericContainer mongo = new GenericContainer("mongo").withExposedPorts(27017);
    
    @BeforeEach
    public void before() throws Exception {
        mongoTemplate = new MongoTemplate(new MongoClient(mongo.getContainerIpAddress(), mongo.getFirstMappedPort()), "test");
    }
    
    @Test
    public void contextLoads() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Steve");
        mongoTemplate.save(map, "someCollection");
    }
}
