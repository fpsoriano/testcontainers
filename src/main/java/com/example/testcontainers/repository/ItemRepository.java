package com.example.testcontainers.repository;

import com.example.testcontainers.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
