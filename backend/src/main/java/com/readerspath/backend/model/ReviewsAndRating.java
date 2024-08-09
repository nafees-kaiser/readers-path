package com.readerspath.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
