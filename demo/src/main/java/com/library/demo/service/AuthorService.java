package com.library.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.library.demo.domain.AuthorDO;
import com.library.demo.domain.BookDO;
import com.library.demo.dto.AuthorDTO;
import com.library.demo.dto.BookDTO;
import com.library.demo.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorDTO createAuthor(AuthorDO author) {
        AuthorDO newAuthor = authorRepository.save(author);
        return newAuthor.toAuthorDTO();
    }

    @Override
    public AuthorDTO updateAuthor(Long authorId, AuthorDO author) {
        Optional<AuthorDO> currAuthor = authorRepository.findById(authorId);
        if (currAuthor.isPresent()){
            currAuthor.get().setName(author.getName());
            currAuthor.get().setSurname(author.getSurname());
            currAuthor.get().setDescription(author.getDescription());
            authorRepository.save(currAuthor.get());
            return currAuthor.get().toAuthorDTO();
        }
        return null;
    }

    @Override
    public AuthorDTO getAuthor(Long authorId) {
        Optional<AuthorDO> author = authorRepository.findById(authorId);
        if(author.isPresent()){
            return author.get().toAuthorDTO();
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDO> authors = authorRepository.findAll();
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for( AuthorDO author : authors){
            authorsDTO.add(author.toAuthorDTO());
        }
        return authorsDTO;
    }

    @Override
    public List<BookDTO> getBooks(Long authorId) {
        Optional<AuthorDO> author = authorRepository.findById(authorId);
        if(author.isPresent()){
            List<BookDO> books= new ArrayList<>(author.get().getBooks());
            List<BookDTO> bookDTOs = new ArrayList<>();
            for( BookDO book: books){
                bookDTOs.add(book.toBookDTO());
            }
            return bookDTOs;
        }
        return null;
    }

    
    
}
