package com.library.demo.controller;

import java.util.List;

import com.library.demo.domain.AuthorDO;
import com.library.demo.dto.AuthorDTO;
import com.library.demo.dto.BookDTO;
import com.library.demo.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @ApiOperation("Creates a new author")
    @PostMapping(value="/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDO author){
        AuthorDTO newAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(newAuthor, HttpStatus.CREATED); 
    }

    @ApiOperation("Updates an author by id")
    @PutMapping(value="/authors/{authorId}", consumes="application/json", produces="application/json")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorDO author) {
        AuthorDTO currAuthor = authorService.updateAuthor(authorId, author);
        return new ResponseEntity<>(currAuthor, HttpStatus.CREATED);
    }

    @ApiOperation("Deletes an author by id")
    @DeleteMapping(value="/authors/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable(value="authorId") Long authorId){
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>("Deleted Author ID: " + authorId, HttpStatus.OK);
    }

    @ApiOperation("Gets an author by id")
    @GetMapping(value="/authors/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable(value="authorId") Long authorId){
        AuthorDTO currAuthor = authorService.getAuthor(authorId);
        return new ResponseEntity<>(currAuthor, HttpStatus.OK);
    }

    @ApiOperation("Gets all authors")
    @GetMapping(value="/authors")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
        List<AuthorDTO> allAuthors = authorService.getAllAuthors();
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @ApiOperation("Gets books of an author by author id")
    @GetMapping(value="/authors/books/{authorId}")
    public ResponseEntity<List<BookDTO>> getBooks(@PathVariable(value="authorId") Long authorId){
        List<BookDTO> books = authorService.getBooks(authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
