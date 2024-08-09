package com.readerspath.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Preference extends BaseEntity<Long> {

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
    private List<Category> favouriteCategories;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
    private List<Author> favouriteAuthors;
}
