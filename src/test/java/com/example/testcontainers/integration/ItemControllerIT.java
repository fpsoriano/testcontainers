package com.example.testcontainers.integration;

import com.example.testcontainers.model.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemControllerIT extends AbstractIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllItems() throws Exception {

		// WHEN
		List<Item> items = objectMapper.readValue(
				mockMvc.perform(MockMvcRequestBuilders.get("/api/items"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andReturn()
						.getResponse()
						.getContentAsString(),
				new TypeReference<>() {
				});

		// THEN
		assertEquals(2, items.size());
		assertEquals("Item 1", items.get(0).name());
		assertEquals(10.0, items.get(0).price());
		assertEquals("Item 2", items.get(1).name());
		assertEquals(20.0, items.get(1).price());
	}
}