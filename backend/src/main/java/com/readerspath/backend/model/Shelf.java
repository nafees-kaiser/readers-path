package com.readerspath.backend.model;

import com.readerspath.backend.enums.ShelfState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shelf extends BaseEntity<Long>{
    @OneToMany
    private List<Book> books;
    @OneToOne
    @JoinColumn
    private AppUser appUser;
    @Column(columnDefinition = "varchar(255) default 'Wish to read'")
    @Enumerated(EnumType.STRING)
    private ShelfState state;
}
