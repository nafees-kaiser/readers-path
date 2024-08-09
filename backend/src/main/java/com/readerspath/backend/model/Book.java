package com.readerspath.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity<Long>{
    private String title;
    @OneToOne
    @JoinColumn
    private Author author;
    private String publisher;
    private String isbn;
    private String edition;
    private String pageCount;
    @OneToOne
    @JoinColumn
    private Category category;
//    private String bookCover;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<LinksToBuy> links;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<ReviewsAndRating> reviewsAndRating;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Questionaries> questionaries;
    private String overAllRating;
}
