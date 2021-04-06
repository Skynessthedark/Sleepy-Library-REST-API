package com.library.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.demo.domain.AuthorDO;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class TestAuthorController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateAuthor() throws Exception {

		AuthorDO newAuthor = new AuthorDO();
		newAuthor.setName("testName2");
		newAuthor.setSurname("testSurname2");
		newAuthor.setDescription("This author is for test2.");

		mockMvc.perform(post("/api/authors")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(newAuthor)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.surname").exists())
						.andExpect(jsonPath("$.description").exists())
						.andDo(print());
		
	}

	@Test
	public void testUpdateAuthor() throws Exception{
		String authorId = "2";
		AuthorDO currAuthor = new AuthorDO();
		currAuthor.setName("UpdateName");
		currAuthor.setSurname("UpdateSurname");
		currAuthor.setDescription("Update test for author");

		mockMvc.perform(put("/api/authors/{authorId}", authorId)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(currAuthor)))
							.andExpect(jsonPath("$.name").exists())
							.andExpect(jsonPath("$.surname").exists())
							.andExpect(jsonPath("$.description").exists())
							.andDo(print());
	}

	@Test
	public void testDeleteAuthor() throws Exception{
		String authorId = "2";
		
		mockMvc.perform(delete("/api/authors/{authorId}", authorId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetAuthor() throws Exception{
		String authorId = "2";
		mockMvc.perform(get("/api/authors/{authorId}", authorId)
							.accept(MediaType.APPLICATION_JSON))
							.andDo(print())
							.andExpect(status().isOk());
	}

	@Test
	public void testGetAllAuthors() throws Exception{
		mockMvc.perform(get("/api/authors")
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk());
	}

	@Test
	public void testGetBooks() throws Exception{
		String authorId = "1";

		mockMvc.perform(get("/api/authors/books/{authorId}", authorId)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andDo(print());
	}

	private static String asJsonString(Object o) {
		try{
			return new ObjectMapper().writeValueAsString(o);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

}
