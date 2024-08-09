package com.readerspath.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewsAndRating extends BaseEntity<Long> {
    // TODO: make appUser and book unique
    private String review;
    private String rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AppUser appUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;

}
