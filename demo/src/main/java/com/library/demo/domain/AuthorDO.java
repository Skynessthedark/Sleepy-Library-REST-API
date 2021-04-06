package com.library.demo.domain;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.demo.dto.AuthorDTO;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="authors")
@JsonIgnoreProperties(value = {"books"})
public class AuthorDO implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String surname;
    
    private String description;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @EqualsAndHashCode.Exclude Set<BookDO> books = new HashSet<>();

    public AuthorDTO toAuthorDTO(){
        AuthorDTO newDto = new AuthorDTO();
        newDto.setName(this.name);
        newDto.setSurname(this.surname);
        newDto.setDescription(this.description);
        for(BookDO book: this.books){
            newDto.getBooks().add(book);
        }
        return newDto;
    }
}
