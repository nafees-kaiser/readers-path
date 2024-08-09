package com.readerspath.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Shelf extends BaseEntity<Long> {
    @OneToMany
    private List<Book> books;

    @OneToOne
    @JoinColumn
    private AppUser appUser;

    public void setBooks(Book book) {
        if (this.books == null) {
            books = new ArrayList<>();
        }
        if (!books.contains(book)) {
            books.add(book);
        }
    }
}
