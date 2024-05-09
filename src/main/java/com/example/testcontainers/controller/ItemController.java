package com.example.testcontainers.controller;

import com.example.testcontainers.model.Item;
import com.example.testcontainers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	@PostMapping
	public void addItem(@RequestBody Item item) {
		itemService.addItem(item);
	}
}
