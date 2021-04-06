package com.library.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.demo.domain.AuthorDO;
import com.library.demo.domain.BookDO;

import lombok.Data;

@Data
public class AuthorDTO implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String description;
    @JsonIgnore
    private Set<BookDO> books = new HashSet<>();

    public AuthorDO toAuthorDO(){
        AuthorDO newDo = new AuthorDO();
        newDo.setName(this.name);
        newDo.setSurname(this.surname);
        newDo.setDescription(this.description);
        for(BookDO book: this.books){
            newDo.getBooks().add(book);
        }
        return newDo;
    }

}
