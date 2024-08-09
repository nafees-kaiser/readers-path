package com.readerspath.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Questionaries extends BaseEntity<Long> {
    private String question;
    private String answer;
    @ManyToOne
    @JoinColumn
    private AppUser askedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;
}
