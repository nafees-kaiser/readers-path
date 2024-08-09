package com.readerspath.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LinksToBuy extends BaseEntity<Long>{
    private String link;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;
}
