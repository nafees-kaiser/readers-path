package com.readerspath.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsAndRating extends BaseEntity<Long>{

    private String review;
    private String rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AppUser appUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;

}
