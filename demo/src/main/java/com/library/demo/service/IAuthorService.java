package com.library.demo.service;

import java.util.List;

import com.library.demo.domain.AuthorDO;
import com.library.demo.dto.AuthorDTO;
import com.library.demo.dto.BookDTO;

public interface IAuthorService {

    AuthorDTO createAuthor(AuthorDO author);
    AuthorDTO updateAuthor(Long authorId, AuthorDO author);
    AuthorDTO getAuthor(Long authorId);
    void deleteAuthor(Long authorId);
    List<AuthorDTO> getAllAuthors();
    List<BookDTO> getBooks(Long authorId);
}
