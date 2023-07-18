package com.tonycallaghan.restfulAPI;

import com.tonycallaghan.restfulAPI.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Product product;
	private String uuid;

	@BeforeEach
	public void setup() {
		uuid = UUID.randomUUID().toString();
		product = new Product(uuid, "Product1", new BigDecimal("123.45"));
	}

	@Test
	void createProduct_returnsCreated() throws Exception {
		mockMvc.perform(post("/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void getAllProducts_returnsOk() throws Exception {
		mockMvc.perform(get("/product"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void getProductById_returnsOk() throws Exception {
		Product newProduct = createProduct();

		mockMvc.perform(get("/product/{id}", newProduct.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void patchProduct_returnsOk() throws Exception {
		Product newProduct = createProduct();

		Product updatedProduct = new Product(newProduct.getId(), "Updated Product", new BigDecimal("456.78"));

		mockMvc.perform(patch("/product/{id}", newProduct.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updatedProduct)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}


	@Test
	void deleteProduct_returnsOk() throws Exception {
		Product newProduct = createProduct();

		mockMvc.perform(delete("/product/{id}", newProduct.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
	}

	private Product createProduct() throws Exception {
		Product newProduct = new Product(uuid, "Product1", new BigDecimal("123.45"));

		String response = mockMvc.perform(post("/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newProduct)))
				.andReturn()
				.getResponse()
				.getContentAsString();

		return objectMapper.readValue(response, Product.class);
	}
}
