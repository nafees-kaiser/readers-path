package com.readerspath.backend.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class LinksToBuy extends BaseEntity<Long> {
    private String link;
}
