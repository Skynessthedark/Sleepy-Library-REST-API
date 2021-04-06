package com.library.demo.repository;

import java.util.Optional;

import com.library.demo.domain.BookDO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDO, Long> {
    
    Optional<BookDO> findByIsbnNo(String isbn);
}
