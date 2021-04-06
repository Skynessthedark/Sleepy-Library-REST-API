package com.library.demo.repository;

//import java.util.Optional;

import com.library.demo.domain.AuthorDO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorDO, Long> {
    
    //Optional<AuthorDO> findByName(String authorName);
}
