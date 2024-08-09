package com.readerspath.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class LinksToBuy extends BaseEntity<Long> {
    private String link;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;
}
