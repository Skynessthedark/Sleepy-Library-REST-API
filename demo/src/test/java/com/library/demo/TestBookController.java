package com.library.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.demo.domain.AuthorDO;
import com.library.demo.domain.BookDO;
import com.library.demo.domain.PublisherDO;
import com.library.demo.domain.SeriesDO;

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
public class TestBookController {
    
    @Autowired
    MockMvc mockMvc;

    @Test
	public void testCreateBook() throws Exception {
        SeriesDO newSeries = new SeriesDO();
        newSeries.setId(5L);

		BookDO newBook = new BookDO();
		newBook.setTitle("Book6");
		newBook.setSubTitle("");
        newBook.setIsbnNo("1234567896");

		mockMvc.perform(post("/api/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(newBook)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.title", "{1,}$").exists())
						.andExpect(jsonPath("$.isbnNo", "[0-9]{10}$").exists())
                        .andDo(print());
	}

	@Test
	public void testUpdateBook() throws Exception{
		BookDO book = new BookDO();
		book.setId(2L);
		book.setTitle("UpdateTest");
		book.setSubTitle("ST");
		book.setIsbnNo("1234567777");

		mockMvc.perform(put("/api/books")
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(book)))
							.andExpect(status().isCreated())
							.andExpect(jsonPath("$.title", "{1,}$").exists())
							.andExpect(jsonPath("$.isbnNo", "[0-9]{10}$").exists())
                        	.andDo(print());
	}

	@Test
	public void testGetBook() throws Exception{
		String BookId = "1";
		mockMvc.perform(get("/api/books/{bookId}", BookId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetBookByIsbn() throws Exception{
		String bookIsbn = "1234567890";
		mockMvc.perform(get("/api/books/byIsbn/{bookIsbn}", bookIsbn)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetAllBooks() throws Exception{
		mockMvc.perform(get("/api/books")
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andDo(print());
	}

	@Test
	public void testDeleteBook() throws Exception{
		String bookId = "2";
		mockMvc.perform(delete("/api/books/{bookId}", bookId))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetBooks() throws Exception{
		String bookId = "1";
		mockMvc.perform(get("/api/Books/books/{BookId}", bookId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetAuthors() throws Exception{
		String bookId = "1";
		mockMvc.perform(get("/api/books/authors/{bookId}", bookId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testGetPublishers() throws Exception{
		String bookId = "1";
		mockMvc.perform(get("/api/books/publishers/{bookId}", bookId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Test
	public void testAddAuthor() throws Exception{
		AuthorDO author = new AuthorDO();
		author.setId(1L);
		author.setName("Author");
		author.setSurname("One");
		author.setDescription("desc");

		mockMvc.perform(put("/api/books/add-author/{bookId}", 5L)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(author))
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
                        	.andDo(print());
	}

	@Test
	public void testRemoveAuthor() throws Exception{
		AuthorDO author = new AuthorDO();
		author.setId(1L);
		author.setName("Author");
		author.setSurname("One");
		author.setDescription("desc");

		mockMvc.perform(put("/api/books/remove-author/{bookId}", 5L)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(author))
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
                        	.andDo(print());
	}

	@Test
	public void testAddPublisher() throws Exception{
		PublisherDO publisher = new PublisherDO();
		publisher.setId(1L);
		publisher.setName("Publisher");
		publisher.setDescription("desc");

		mockMvc.perform(put("/api/books/add-publisher/{bookId}", 5L)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(publisher))
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
                        	.andDo(print());
	}

	@Test
	public void testRemovePublisher() throws Exception{
		PublisherDO publisher = new PublisherDO();
		publisher.setId(1L);
		publisher.setName("Publisher");
		publisher.setDescription("desc");

		mockMvc.perform(put("/api/books/remove-publisher/{bookId}", 5L)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(publisher))
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
                        	.andDo(print());
	}

	@Test
	public void testAddSeries() throws Exception{
		SeriesDO series = new SeriesDO();
		series.setId(4L);
		series.setName("Harry Potter");

		mockMvc.perform(put("/api/books/add-series/{bookId}", 5L)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(series))
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
                        	.andDo(print());
	}

	@Test
	public void testRemoveSeries() throws Exception{
		SeriesDO series = new SeriesDO();
		series.setId(4L);
		series.setName("Harry Potter");

		mockMvc.perform(put("/api/books/remove-series/{bookId}", 5L)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(series))
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
