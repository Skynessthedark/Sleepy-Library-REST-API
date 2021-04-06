package com.library.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.demo.domain.BookDO;
import com.library.demo.domain.SeriesDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class SeriesDTO implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    
    private String name;

    @JsonIgnore
    @EqualsAndHashCode.Exclude private Set<BookDO> books = new HashSet<>();

    public SeriesDO toSeriesDO(){
        SeriesDO newDto = new SeriesDO();
        newDto.setName(this.name);
        for(BookDO book: this.books){
            newDto.getBooks().add(book);
        }
        return newDto;
    }
}
