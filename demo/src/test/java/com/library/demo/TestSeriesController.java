package com.library.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.demo.domain.SeriesDO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestSeriesController {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateSeries() throws Exception{
        
        SeriesDO newSeries = new SeriesDO();
        newSeries.setName("Series2");

        mockMvc.perform(post("/api/series")
                        .content(asJsonString(newSeries))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name", "{1, 256}$").exists())
                        .andDo(print());
    }

    @Test
    public void testUpdateSeries() throws Exception{
        String seriesId = "1";
        SeriesDO newSeries = new SeriesDO();
        newSeries.setId(1L);
        newSeries.setName("TestUpdateName");

        mockMvc.perform(put("/api/series/{seriesId}", seriesId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newSeries)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name").exists())
                        .andDo(print());
    }

    @Test
    public void testDeleteSeries() throws Exception{
        String seriesId = "1";

        mockMvc.perform(delete("/api/series/{seriesId}", seriesId)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andDo(print());
    }

    @Test
	public void testGetAllSeries() throws Exception{
		mockMvc.perform(get("/api/series")
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andDo(print());
	}

	@Test
	public void testGetSeries() throws Exception{
		String seriesId = "1";
		mockMvc.perform(get("/api/series/byId/{seriesId}", seriesId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

    @Test
	public void testGetSeriesByName() throws Exception{
		String seriesName = "Harry Potter";
		mockMvc.perform(get("/api/series/byName/{seriesName}", seriesName)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

    @Test
	public void testGetBooks() throws Exception{
		String seriesId = "1";
		mockMvc.perform(get("/api/series/books/{seriesId}", seriesId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

    public String asJsonString(Object o){
        try{
            return new ObjectMapper().writeValueAsString(o);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
