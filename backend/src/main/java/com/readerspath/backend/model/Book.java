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

    @OneToOne
    @JoinColumn
    private Image coverImage;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<LinksToBuy> links;

    //    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReviewsAndRating> reviewsAndRating;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Questionaries> questionaries;

    private String overAllRating;

    public Long getReviewsCount() {
        if (reviewsAndRating == null) return 0L;
        else {
            return (long) reviewsAndRating.size();
        }

    }

}
