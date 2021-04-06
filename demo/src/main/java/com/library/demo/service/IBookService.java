package com.library.demo.service;

import java.util.List;

import com.library.demo.domain.AuthorDO;
import com.library.demo.domain.BookDO;
import com.library.demo.domain.PublisherDO;
import com.library.demo.domain.SeriesDO;
import com.library.demo.dto.AuthorDTO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.PublisherDTO;

public interface IBookService {
    
    BookDTO createBook(BookDO book);
    BookDTO updateBook(BookDO book);
    void deleteBook(Long bookId);

    BookDTO getBook(Long bookId);
    BookDTO getBookByIsbn(String bookIsbn);
    List<BookDTO> getAllBooks();

    List<AuthorDTO> getAuthors(Long bookId);
    List<PublisherDTO> getPublishers(Long bookId);

    BookDTO addAuthor(Long bookId, AuthorDO author);
    BookDTO removeAuthor(Long bookId, AuthorDO author);

    BookDTO addPublisher(Long bookId, PublisherDO publisher);
    BookDTO removePublisher(Long bookId, PublisherDO publisher);
    
    BookDTO addSeries(Long bookId, SeriesDO series);
    BookDTO removeSeries(Long bookId, SeriesDO series);
}

