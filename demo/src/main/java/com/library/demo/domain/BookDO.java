package com.library.demo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.library.demo.dto.BookDTO;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import lombok.Data;

@Data
@Entity
@Table(name="books")
public class BookDO implements Serializable{
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;
    private String subTitle;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="series_id", referencedColumnName = "id")
    private SeriesDO series;

    @Column(nullable=false, unique = true)
    private String isbnNo;

    //Authors can be more than one.
    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name="author_book",
        joinColumns = @JoinColumn(name="book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName = "id")
    )
    Set<AuthorDO> authors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name="publisher_book",
        joinColumns = @JoinColumn(name="book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="publisher_id", referencedColumnName = "id")
    )
    Set<PublisherDO> publishers = new HashSet<>();

    public void addAuthor(AuthorDO author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(AuthorDO author){
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void addPublisher(PublisherDO publisher){
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }

    public void removePublisher(PublisherDO publisher){
        this.publishers.remove(publisher);
        publisher.getBooks().remove(this);
    }

    public void addSeries(SeriesDO series){
        this.series = series;
        series.getBooks().add(this);
    }

    public void removeSeries(SeriesDO series){
        this.series = null;
        series.getBooks().remove(this);
    }

    public BookDTO toBookDTO(){
        BookDTO newDo = new BookDTO();
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
