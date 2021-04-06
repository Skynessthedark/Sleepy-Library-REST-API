package com.library.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.library.demo.domain.BookDO;
import com.library.demo.domain.SeriesDO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.SeriesDTO;
import com.library.demo.repository.SeriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesService implements ISeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public SeriesDTO createSeries(SeriesDO series) {
        SeriesDO newSeries = seriesRepository.save(series);
        return newSeries.toSeriesDTO();
    }

    @Override
    public SeriesDTO updateSeries(Long seriesId, SeriesDO series) {
        Optional<SeriesDO> currSeries = seriesRepository.findById(seriesId);
        if(currSeries.isPresent()){
            currSeries.get().setName(series.getName());
            SeriesDO savedSeries = seriesRepository.save(currSeries.get());
            return savedSeries.toSeriesDTO();
        }
        return null;
    }

    @Override
    public void deleteSeries(Long seriesId) {
        seriesRepository.deleteById(seriesId);

    }

    @Override
    public SeriesDTO getSeries(Long seriesId) {
        Optional<SeriesDO> series = seriesRepository.findById(seriesId);
        if(series.isPresent()) return series.get().toSeriesDTO();
        return null;
    }

    @Override
    public List<SeriesDTO> getAllSeries() {
        List<SeriesDO> series = seriesRepository.findAll();
        List<SeriesDTO> seriesDtos = new ArrayList<>();
        for(SeriesDO seri: series) seriesDtos.add(seri.toSeriesDTO());
        return seriesDtos;
    }

    @Override
    public List<BookDTO> getBooks(Long seriesId) {
        Optional<SeriesDO> series = seriesRepository.findById(seriesId);
        if(series.isPresent()){
            List<BookDTO> bookDtos = new ArrayList<>();
            Set<BookDO> bookSet = series.get().getBooks();
            List<BookDO> books = new ArrayList<>(bookSet);
            for(BookDO book: books) bookDtos.add(book.toBookDTO());
            return bookDtos;
        }
        return null;
    }

    @Override
    public List<SeriesDTO> getSeries(String seriesName) {
        List<SeriesDO> series = seriesRepository.findAllByName(seriesName);
        List<SeriesDTO> seriesDtos = new ArrayList<>();
        for(SeriesDO seri: series) seriesDtos.add(seri.toSeriesDTO());
        return seriesDtos;
    }
    
}
