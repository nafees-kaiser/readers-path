package com.readerspath.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reward extends BaseEntity<Long> {
    @OneToOne
    @JoinColumn
    private AppUser appUser;
    @Column(columnDefinition = "varchar(255) default 0")
    private Long completedBooks;
    private Long coins;

    @PrePersist
    @PreUpdate
    private void updateCoins() {
        if (this.completedBooks != null && this.completedBooks >= 20) {
            this.coins = this.completedBooks * 20;
        }
    }
}
