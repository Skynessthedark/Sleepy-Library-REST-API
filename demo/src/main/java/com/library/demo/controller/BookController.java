package com.library.demo.controller;

import java.util.List;

import com.library.demo.domain.AuthorDO;
import com.library.demo.domain.BookDO;
import com.library.demo.domain.PublisherDO;
import com.library.demo.domain.SeriesDO;
import com.library.demo.dto.AuthorDTO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.PublisherDTO;
import com.library.demo.service.BookService;

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
public class BookController {
    
    @Autowired
    private BookService bookService;

    @ApiOperation("Creates a new book")
    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDO book){
        BookDTO newBook = bookService.createBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @ApiOperation("Updates a book")
    @PutMapping("/books")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDO book){
        BookDTO currBook = bookService.updateBook(book);
        return new ResponseEntity<>(currBook, HttpStatus.CREATED);
    }

    @ApiOperation("Deletes a book by id")
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Deleted book: "+ bookId, HttpStatus.OK);
    }

    @ApiOperation("Gets a book by id")
    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long bookId){
        BookDTO currBook = bookService.getBook(bookId);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }

    @ApiOperation("Gets a book by ISBN number")
    @GetMapping("/books/byIsbn/{bookIsbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String bookIsbn){
        BookDTO currBook = bookService.getBookByIsbn(bookIsbn);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }

    @ApiOperation("Gets all books")
    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @ApiOperation("Gets the authors of a book by id")
    @GetMapping("/books/authors/{bookId}")
    public ResponseEntity<List<AuthorDTO>> getAuthors(@PathVariable Long bookId){
        List<AuthorDTO> authors = bookService.getAuthors(bookId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @ApiOperation("Gest the publishers of a book by id")
    @GetMapping("/books/publishers/{bookId}")
    public ResponseEntity<List<PublisherDTO>> getPublishers(@PathVariable Long bookId){
        List<PublisherDTO> publishers = bookService.getPublishers(bookId);
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @ApiOperation("Adds an author into book by id")
    @PutMapping("/books/add-author/{bookId}")
    public ResponseEntity<BookDTO> addAuthor(@PathVariable Long bookId, @RequestBody AuthorDO author){
        BookDTO currBook = bookService.addAuthor(bookId, author);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }

    @ApiOperation("Removes an author from book by id")
    @PutMapping("/books/remove-author/{bookId}")
    public ResponseEntity<BookDTO> removeAuthor(@PathVariable Long bookId, @RequestBody AuthorDO author){
        BookDTO currBook = bookService.removeAuthor(bookId, author);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }

    @ApiOperation("Adds a publisher into a book by id")
    @PutMapping("/books/add-publisher/{bookId}")
    public ResponseEntity<BookDTO> addPublisher(@PathVariable Long bookId, @RequestBody PublisherDO publisher){
        BookDTO currBook = bookService.addPublisher(bookId, publisher);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }

    @ApiOperation("Removes a publisher from a book by id")
    @PutMapping("/books/remove-publisher/{bookId}")
    public ResponseEntity<BookDTO> removePublisher(@PathVariable Long bookId, @RequestBody PublisherDO publisher){
        BookDTO currBook = bookService.removePublisher(bookId, publisher);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }

    @ApiOperation("Adds the series to a book by id")
    @PutMapping("/books/add-series/{bookId}")
    public ResponseEntity<BookDTO> addSeries(@PathVariable Long bookId, @RequestBody SeriesDO series){
        BookDTO currBook = bookService.addSeries(bookId, series);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }

    @ApiOperation("Removes the series from a book by id")
    @PutMapping("/books/remove-series/{bookId}")
    public ResponseEntity<BookDTO> removeSeries(@PathVariable Long bookId, @RequestBody SeriesDO series){
        BookDTO currBook = bookService.removeSeries(bookId, series);
        return new ResponseEntity<>(currBook, HttpStatus.OK);
    }
}
