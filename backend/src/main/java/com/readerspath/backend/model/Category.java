package com.readerspath.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category extends BaseEntity<Long> {
    @Column
    private String name;
}
