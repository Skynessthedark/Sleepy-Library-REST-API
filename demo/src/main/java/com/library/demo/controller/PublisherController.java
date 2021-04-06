package com.library.demo.controller;

import java.util.List;

import com.library.demo.domain.PublisherDO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.PublisherDTO;
import com.library.demo.service.PublisherService;

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
@RequestMapping("/api")
public class PublisherController {
    
    @Autowired
    PublisherService publisherService;

    @ApiOperation("Creates a publisher")
    @PostMapping(value="/publishers")
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherDO publisher){
        PublisherDTO newPublisher = publisherService.createPublisher(publisher);
        return new ResponseEntity<>(newPublisher, HttpStatus.CREATED);
    }

    @ApiOperation("Updates a publisher by id")
    @PutMapping(value="/publishers/{publisherId}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable Long publisherId, @RequestBody PublisherDO publisher){
        PublisherDTO currPublisher = publisherService.updatePublisher(publisherId, publisher);
        return new ResponseEntity<>(currPublisher, HttpStatus.CREATED);
    }

    @ApiOperation("Gets a publisher by id")
    @GetMapping(value="/publishers/{publisherId}")
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable Long publisherId){
        PublisherDTO currPublisher = publisherService.getPublisher(publisherId);
        return new ResponseEntity<>(currPublisher, HttpStatus.OK);
    }

    @ApiOperation("Gets all publishers")
    @GetMapping(value="/publishers")
    public ResponseEntity<List<PublisherDTO>> getAllPublishers(){
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @ApiOperation("Deletes a publisher by id")
    @DeleteMapping(value = "/publishers/{publisherId}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long publisherId){
        publisherService.deletePublisher(publisherId);
        return new ResponseEntity<>("Deleted publisher: "+publisherId, HttpStatus.OK);
    }

    @ApiOperation("Gets the books of a publisher")
    @GetMapping(value = "/publishers/books/{publisherId}")
    public ResponseEntity<List<BookDTO>> getBooks(@PathVariable Long publisherId){
        List<BookDTO> books = publisherService.getBooks(publisherId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
