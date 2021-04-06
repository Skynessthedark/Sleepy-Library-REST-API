package com.library.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.library.demo.domain.BookDO;
import com.library.demo.domain.PublisherDO;
import com.library.demo.dto.BookDTO;
import com.library.demo.dto.PublisherDTO;
import com.library.demo.repository.PublisherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IPublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public PublisherDTO createPublisher(PublisherDO publisher) {
        PublisherDO newPublisher = publisherRepository.save(publisher);
        return newPublisher.toPublisherDTO();
    }

    @Override
    public PublisherDTO updatePublisher(Long publisherId, PublisherDO publisher) {
        Optional<PublisherDO> currPublisher = publisherRepository.findById(publisherId);
        if(currPublisher.isPresent()){
            currPublisher.get().setName(publisher.getName());
            currPublisher.get().setDescription(publisher.getDescription());
            return currPublisher.get().toPublisherDTO();
        }
        return null;
    }

    @Override
    public PublisherDTO getPublisher(Long publisherId) {
        Optional<PublisherDO> publisher = publisherRepository.findById(publisherId);
        if(publisher.isPresent()) return publisher.get().toPublisherDTO();
        return null;
    }

    @Override
    public void deletePublisher(Long publisherId) {
        publisherRepository.deleteById(publisherId);
    }

    @Override
    public List<PublisherDTO> getAllPublishers() {
        List<PublisherDO> publishers = publisherRepository.findAll();
        List<PublisherDTO> publisherDTOs = new ArrayList<>();
        for (PublisherDO publisher: publishers){
            publisherDTOs.add(publisher.toPublisherDTO());
        }
        return publisherDTOs;
    }

    @Override
    public List<BookDTO> getBooks(Long publisherId) {
        Optional<PublisherDO> publisher = publisherRepository.findById(publisherId);
        if(publisher.isPresent()){
            Set<BookDO> books = publisher.get().getBooks();
            List<BookDO> bookList = new ArrayList<>(books);
            List<BookDTO> bookDTOs = new ArrayList<>();
            for(BookDO book: bookList){
                bookDTOs.add(book.toBookDTO());
            }
            return bookDTOs;
        }
        return null;
    }   
}
