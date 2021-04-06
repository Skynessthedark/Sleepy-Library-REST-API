package com.library.demo.repository;

import com.library.demo.domain.PublisherDO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherDO, Long>{
    
}
