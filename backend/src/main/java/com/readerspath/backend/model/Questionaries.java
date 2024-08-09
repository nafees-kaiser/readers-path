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
public class Questionaries extends BaseEntity<Long>{
    private String question;
    private String answer;
    @OneToOne
    @JoinColumn
    private AppUser askedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;
}
