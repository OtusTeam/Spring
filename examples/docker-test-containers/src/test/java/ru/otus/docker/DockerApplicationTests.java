package ru.otus.docker;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DockerApplicationTests {
    private static final int MONGO_PORT = 27017;
    private static final String SOME_COLLECTION_NAME = "someCollection";
    private static final String IMAGE_NAME = "mongo";

    @SuppressWarnings("resource")
    public static GenericContainer<?> mongo = new GenericContainer<>(DockerImageName.parse(IMAGE_NAME))
            .withExposedPorts(MONGO_PORT);

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongo.start();
        registry.add("spring.data.mongodb.port", mongo::getFirstMappedPort);
        registry.add("spring.data.mongodb.host", mongo::getHost);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void shouldSaveAndLoadDocument() {
        Document document = Document.parse("{name: \"Steve\"}");
        Document savedDocument = mongoTemplate.save(document, SOME_COLLECTION_NAME);
        assertThat(savedDocument.get("_id")).isNotNull();
    }
}
