package com.readerspath.backend.model;

import com.readerspath.backend.enums.ShelfState;
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
                name = "shelfUniqueConstraint",
                columnNames = {"book_id", "app_user_id"}
        )
})
public class Shelf extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private AppUser appUser;

    @Enumerated(EnumType.STRING)
    private ShelfState state;
}
