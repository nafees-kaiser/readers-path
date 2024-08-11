package com.readerspath.backend.model;

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
    // TODO: image handle
//       private String bookCover;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<LinksToBuy> links;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ReviewsAndRating> reviewsAndRating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Questionaries> questionaries;

    private String overAllRating;

}
