package com.library.demo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.demo.dto.PublisherDTO;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="publishers")
public class PublisherDO implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "publishers", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    @EqualsAndHashCode.Exclude Set<BookDO> books = new HashSet<>();
    
    public PublisherDTO toPublisherDTO(){
        PublisherDTO newDto = new PublisherDTO();
        newDto.setName(this.name);
        newDto.setDescription(this.description);
        for(BookDO book: this.books){
            newDto.getBooks().add(book);
        }
        return newDto;
    }
}
