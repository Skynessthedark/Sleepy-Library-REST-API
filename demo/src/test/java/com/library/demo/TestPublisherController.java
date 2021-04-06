package com.library.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.demo.domain.PublisherDO;

import org.junit.Test;
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

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestPublisherController {
    
    @Autowired
    MockMvc mockMvc;

    @Test
	public void testCreatePublisher() throws Exception {

		PublisherDO newPublisher = new PublisherDO();
		newPublisher.setName("For Delete");
		newPublisher.setDescription("createDescription");

		mockMvc.perform(post("/api/publishers")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(newPublisher)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.description").exists());
		
	}

	@Test
	public void testUpdatePublisher() throws Exception{
		String publisherId = "1";
		PublisherDO publisher = new PublisherDO();
		publisher.setName("İş Bankası Yayınları");
		publisher.setDescription("updateDescription");

		mockMvc.perform(put("/api/publishers/{publisherId}", publisherId)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(publisher)))
							.andExpect(status().isCreated())
							.andExpect(jsonPath("$.name").exists())
							.andExpect(jsonPath("$.description").exists())
							.andDo(print());
	}

	@Test
	public void testGetPublisher() throws Exception{
		String publisherId = "1";
		mockMvc.perform(get("/api/publishers/{publisherId}", publisherId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetAllPublishers() throws Exception{
		mockMvc.perform(get("/api/publishers")
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andDo(print());
	}

	@Test
	public void testDeletePublisher() throws Exception{
		String publisherId = "2";
		mockMvc.perform(delete("/api/publishers/{publisherId}", publisherId))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetBooks() throws Exception{
		String publisherId = "1";
		mockMvc.perform(get("/api/publishers/books/{publisherId}", publisherId)
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
