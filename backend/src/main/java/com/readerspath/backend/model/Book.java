package com.readerspath.backend.model;

import com.readerspath.backend.enums.ShelfState;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book extends BaseEntity<Long> {
    private String title;

    @ManyToOne
    @JoinColumn
    private Author author;

    private String publisher;
    private String isbn;
    private String edition;
    private String pageCount;

    @ManyToOne
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

    @Enumerated(EnumType.STRING)
    private ShelfState state;
}
