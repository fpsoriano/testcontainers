package com.example.testcontainers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
public record Item (@Id String id, String name, double price) {}
