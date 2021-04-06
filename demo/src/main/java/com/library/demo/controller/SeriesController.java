package com.library.demo.controller;

import java.util.List;

import com.library.demo.domain.SeriesDO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.SeriesDTO;
import com.library.demo.service.SeriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
public class SeriesController {
    
    @Autowired
    private SeriesService seriesService;

    @ApiOperation("Creates a series")
    @PostMapping("/series")
    public ResponseEntity<SeriesDTO> createSeries(@RequestBody SeriesDO series){
        SeriesDTO newSeries = seriesService.createSeries(series);
        return new ResponseEntity<>(newSeries, HttpStatus.CREATED);
    }

    @ApiOperation("Updates a series by id")
    @PutMapping("/series/{seriesId}")
    public ResponseEntity<SeriesDTO> updateSeries(@PathVariable Long seriesId, @RequestBody SeriesDO series){
        SeriesDTO currSeries = seriesService.updateSeries(seriesId, series);
        return new ResponseEntity<>(currSeries, HttpStatus.CREATED);
    }

    @ApiOperation("Deletes a series by id")
    @DeleteMapping("/series/{seriesId}")
    public ResponseEntity<String> deleteSeries(@PathVariable Long seriesId){
        seriesService.deleteSeries(seriesId);
        return new ResponseEntity<>("Deleted Series: " + seriesId, HttpStatus.OK);
    }

    @ApiOperation("Gets all series")
    @GetMapping(value="/series")
    public ResponseEntity<List<SeriesDTO>> getAllPublishers(){
        List<SeriesDTO> series = seriesService.getAllSeries();
        return new ResponseEntity<>(series, HttpStatus.OK);
    }
    
    @ApiOperation("Gets a series by id")
    @GetMapping(value="/series/byId/{seriesId}")
    public ResponseEntity<SeriesDTO> getSeries(@PathVariable Long seriesId){
        SeriesDTO currSeries = seriesService.getSeries(seriesId);
        return new ResponseEntity<>(currSeries, HttpStatus.OK);
    }

    @ApiOperation("Gets all series by name")
    @GetMapping(value="/series/byName/{seriesName}")
    public ResponseEntity<List<SeriesDTO>> getSeries(@PathVariable String seriesName){
        List<SeriesDTO> currSeries = seriesService.getSeries(seriesName);
        return new ResponseEntity<>(currSeries, HttpStatus.OK);
    }
    
    @ApiOperation("Gets the books of a series")
    @GetMapping(value = "/series/books/{seriesId}")
    public ResponseEntity<List<BookDTO>> getBooks(@PathVariable Long seriesId){
        List<BookDTO> books = seriesService.getBooks(seriesId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
