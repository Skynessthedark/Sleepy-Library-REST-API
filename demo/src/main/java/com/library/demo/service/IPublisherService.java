package com.library.demo.service;

import java.util.List;

import com.library.demo.domain.PublisherDO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.PublisherDTO;

public interface IPublisherService {
    
    PublisherDTO createPublisher(PublisherDO publisher);
    PublisherDTO updatePublisher(Long pubisherId, PublisherDO publisher);
    PublisherDTO getPublisher(Long publisherId);
    void deletePublisher(Long publisherId);
    List<PublisherDTO> getAllPublishers();
    List<BookDTO> getBooks(Long publisherId);
}
