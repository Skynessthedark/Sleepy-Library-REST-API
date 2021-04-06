package com.library.demo.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.demo.dto.SeriesDTO;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="series")
public class SeriesDO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="book_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude Set<BookDO> books = new HashSet<>();

    public SeriesDTO toSeriesDTO(){
        SeriesDTO newDto = new SeriesDTO();
        newDto.setName(this.name);
        for(BookDO book: this.books){
            newDto.getBooks().add(book);
        }
        return newDto;
    }
}
