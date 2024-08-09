package com.readerspath.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author extends BaseEntity<Long> {
    @Column
    private String name;
    @OneToOne
    @JoinColumn
    private AppUser appUser;
}
