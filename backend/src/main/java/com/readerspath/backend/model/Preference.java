package com.readerspath.backend.model;

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
public class Preference extends BaseEntity<Long>{

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
    private List<Category> favouriteCategories;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
    private List<Author> favouriteAuthors;
}
