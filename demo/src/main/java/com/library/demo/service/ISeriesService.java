package com.library.demo.service;

import java.util.List;

import com.library.demo.domain.SeriesDO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.SeriesDTO;

public interface ISeriesService {
    
    SeriesDTO createSeries(SeriesDO series);
    SeriesDTO updateSeries(Long seriesId, SeriesDO series);
    void deleteSeries(Long seriesId);
    SeriesDTO getSeries(Long seriesId);
    List<SeriesDTO> getSeries(String seriesName);
    List<SeriesDTO> getAllSeries();
    List<BookDTO> getBooks(Long seriesId);
}
