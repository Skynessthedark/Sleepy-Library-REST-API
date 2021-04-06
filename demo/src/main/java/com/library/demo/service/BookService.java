package com.library.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.library.demo.domain.AuthorDO;
import com.library.demo.domain.BookDO;
import com.library.demo.domain.PublisherDO;
import com.library.demo.domain.SeriesDO;
import com.library.demo.dto.AuthorDTO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.PublisherDTO;
import com.library.demo.repository.BookRepository;
import com.library.demo.repository.SeriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired SeriesRepository seriesRepository;

    @Override
    public BookDTO createBook(BookDO book) {
        BookDO newBook = bookRepository.save(book);
        return newBook.toBookDTO();
    }

    @Override
    public BookDTO updateBook(BookDO book) {
        Optional<BookDO> currBook = bookRepository.findById(book.getId());
        if(currBook.isPresent()){
            currBook.get().setTitle(book.getTitle());
            currBook.get().setSubTitle(book.getSubTitle());
            currBook.get().setIsbnNo(book.getIsbnNo());
            BookDO savedBook = bookRepository.save(currBook.get());
            return savedBook.toBookDTO();
        }
        return null;
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);

    }

    @Override
    public BookDTO getBook(Long bookId) {
        Optional<BookDO> currBook = bookRepository.findById(bookId);
        if(currBook.isPresent()){
            return currBook.get().toBookDTO();
        }
        return null;
    }

    @Override
    public BookDTO getBookByIsbn(String bookIsbn) {
        Optional<BookDO> currBook = bookRepository.findByIsbnNo(bookIsbn);
        if(currBook.isPresent()){
            return currBook.get().toBookDTO();
        }
        return null;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDO> books = bookRepository.findAll();
        List<BookDTO> bookDtos = new ArrayList<>();
        for(BookDO book: books){
            bookDtos.add(book.toBookDTO());
        }
        return bookDtos;
    }

    @Override
    public List<AuthorDTO> getAuthors(Long bookId) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            List<AuthorDO> authors= new ArrayList<>(book.get().getAuthors());
            List<AuthorDTO> authorDTOs = new ArrayList<>();
            for( AuthorDO author: authors){
                authorDTOs.add(author.toAuthorDTO());
            }
            return authorDTOs;
        }
        return null;
    }

    @Override
    public List<PublisherDTO> getPublishers(Long bookId) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            List<PublisherDO> publishers= new ArrayList<>(book.get().getPublishers());
            List<PublisherDTO> publisherDTOs = new ArrayList<>();
            for( PublisherDO publisher: publishers){
                publisherDTOs.add(publisher.toPublisherDTO());
            }
            return publisherDTOs;
        }
        return null;
    }

    @Override
    public BookDTO addAuthor(Long bookId, AuthorDO author) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            book.get().addAuthor(author);
            BookDO savedBook = bookRepository.save(book.get());
            return savedBook.toBookDTO();
        }
        return null;
    }

    @Override
    public BookDTO removeAuthor(Long bookId, AuthorDO author) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            book.get().removeAuthor(author);
            BookDO savedBook = bookRepository.save(book.get());
            return savedBook.toBookDTO();
        }
        return null;
    }

    @Override
    public BookDTO addPublisher(Long bookId, PublisherDO publisher) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            book.get().addPublisher(publisher);
            BookDO savedBook = bookRepository.save(book.get());
            return savedBook.toBookDTO();
        }
        return null;
    }

    @Override
    public BookDTO removePublisher(Long bookId, PublisherDO publisher) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            book.get().removePublisher(publisher);
            BookDO savedBook = bookRepository.save(book.get());
            return savedBook.toBookDTO();
        }
        return null;
    }

    @Override
    public BookDTO addSeries(Long bookId, SeriesDO series) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            book.get().addSeries(series);
            BookDO savedBook = bookRepository.save(book.get());
            return savedBook.toBookDTO();
        }
        return null;
    }

    @Override
    public BookDTO removeSeries(Long bookId, SeriesDO series) {
        Optional<BookDO> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            book.get().removeSeries(series);
            BookDO savedBook = bookRepository.save(book.get());
            return savedBook.toBookDTO();
        }
        return null;
    }



    
}
