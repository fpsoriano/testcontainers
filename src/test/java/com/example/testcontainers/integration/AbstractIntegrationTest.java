package com.example.testcontainers.integration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public abstract class AbstractIntegrationTest {

    @Container
    public static GenericContainer mongoDBContainer = new GenericContainer("mongo:latest")
            .withExposedPorts(27017)
            .withReuse(false);

    static MongoClient mongoClient = MongoClients.create();


    @BeforeAll
    public static void setupDatabase() {
        MongoDatabase database = mongoClient.getDatabase("testcontainers");
        MongoCollection<Document> collection = database.getCollection("items");
        collection.drop();

        // Insert sample data
        Document item1 = new Document("name", "Item 1").append("price", 10.0);
        Document item2 = new Document("name", "Item 2").append("price", 20.0);
        collection.insertMany(List.of(item1, item2));
    }


}