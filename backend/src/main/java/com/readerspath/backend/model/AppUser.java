package com.readerspath.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser extends BaseEntity<Long>{
    @Column
    String name;
    String email;
    String password;
    String role;
}