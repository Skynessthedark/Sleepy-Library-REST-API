package com.library.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.demo.domain.AuthorDO;
import com.library.demo.domain.BookDO;
import com.library.demo.domain.PublisherDO;
import com.library.demo.domain.SeriesDO;

import lombok.Data;

@Data
public class BookDTO implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String subTitle;

    @JsonProperty("series")
    private SeriesDO series;
    
    private String isbnNo;

    @JsonProperty("authors")
    private Set<AuthorDO> authors = new HashSet<>();

    @JsonProperty("publishers")
    private Set<PublisherDO> publishers = new HashSet<>();

    public BookDO toBookDO(){
        BookDO newDo = new BookDO();
        newDo.setTitle(this.title);
        newDo.setSubTitle(this.subTitle);
        newDo.setSeries(this.series);
        newDo.setIsbnNo(this.isbnNo);
        for(AuthorDO author: this.authors){
            newDo.getAuthors().add(author);
        }
        for(PublisherDO publisher: this.publishers){
            newDo.getPublishers().add(publisher);
        }
        return newDo;
    }
}
