package com.library.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.demo.domain.BookDO;
import com.library.demo.domain.PublisherDO;

import lombok.Data;


@Data
public class PublisherDTO implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    @JsonIgnore
    private Set<BookDO> books = new HashSet<>();

    public PublisherDO toPublisherDO(){
        PublisherDO newDo = new PublisherDO();
        newDo.setName(this.name);
        newDo.setDescription(this.description);
        for(BookDO book: this.books){
            newDo.getBooks().add(book);
        }
        return newDo;
    }

}
