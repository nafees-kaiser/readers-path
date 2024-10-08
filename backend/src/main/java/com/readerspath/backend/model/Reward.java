package com.readerspath.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reward extends BaseEntity<Long> {
    private static final long COINS_PER_INCREMENT = 20L;
    private static final long BOOK_THRESHOLD = 20L;

    @OneToOne
    @JoinColumn
    private AppUser appUser;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> completedBooks;

    @Column(columnDefinition = "bigint default 20")
    private Long coins = 20L;

    public void updateCoins() {
        if (this.completedBooks != null) {
            long inc = this.completedBooks.size() / BOOK_THRESHOLD;

            if (this.coins == null) this.coins = 20L;

            this.coins = inc * COINS_PER_INCREMENT + 20L;
        }
    }

    public void setCompletedBooks(Book book) {
        if (this.completedBooks == null) {
            this.completedBooks = new ArrayList<>();
        }
        if (!this.completedBooks.contains(book)) {
            this.completedBooks.add(book);
        }

    }

    public String getCompletedBooksCount() {
        if (this.completedBooks == null) return "0";
        return Long.toString(this.completedBooks.size());
    }

    public String getCoins() {
        return Long.toString(this.coins);
    }
}
