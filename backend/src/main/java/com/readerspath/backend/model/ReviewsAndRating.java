package com.readerspath.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "reviewsAndRatingUniqueConstraint",
                columnNames = {"book_id", "app_user_id"}
        )
})
public class ReviewsAndRating extends BaseEntity<Long> {

    private String review;
    private String rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;

}
