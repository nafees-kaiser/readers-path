package com.readerspath.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecommendedBooks extends BaseEntity<Long> {
    @ManyToMany
    private List<Book> books;

    @OneToOne
    @JoinColumn
    private AppUser appUser;
}
